package service.transactions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import service.transactions.client.AccountClientConnector;
import service.transactions.client.dto.RequestAccountDto;
import service.transactions.dto.TransactionRequestDto;
import service.transactions.dto.TransactionResponseDto;
import service.transactions.entities.Transaction;
import service.transactions.mapper.MapperToTransactionResponse;
import service.transactions.model.TransactionType;
import service.transactions.repository.TransactionsRepository;
import service.transactions.service.StrategyTransactionsService;
import service.transactions.util.Utility;

@Service
public class ConcreteStrategyDeposit implements StrategyTransactionsService {

    @Autowired
    private AccountClientConnector accountClientConnector;
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private MapperToTransactionResponse mapperToTransactionResponse;


    @Override
    public Mono<TransactionResponseDto> executedTransaction(Mono<TransactionRequestDto> transactionRequest) {
        return transactionRequest
                .flatMap(transactionRequestDto -> accountClientConnector.getAccountByProductId(transactionRequestDto.getProductId())
                        .flatMap(accountServiceResponse -> {
                            RequestAccountDto requestAccountDto = RequestAccountDto.builder()
                                    .accountType(transactionRequestDto.getProductId())
                                    .openingAmount(accountServiceResponse.getOpeningBalance() + transactionRequestDto.getAmount())
                                    .build();
                            return accountClientConnector.updateAccountBalanceByProductId(transactionRequestDto.getProductId(), requestAccountDto)
                                    .flatMap(accountServiceUpdateResponse -> {
                                        Transaction transaction = Transaction.builder()
                                                .typeTransaction("DEPOSIT")
                                                .amount(transactionRequestDto.getAmount())
                                                .dateTransaction(Utility.getDateTimeNow())
                                                .typeTransaction(accountServiceUpdateResponse.getId())
                                                .build();
                                        return transactionsRepository.save(transaction)
                                                .map(mapperToTransactionResponse);
                                    });
                        }));
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.DEPOSIT;
    }
}
