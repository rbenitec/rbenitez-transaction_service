package service.transactions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import service.transactions.client.CreditClientConnector;
import service.transactions.client.dto.UpdateCreditRequestDto;
import service.transactions.dto.TransactionRequestDto;
import service.transactions.dto.TransactionResponseDto;
import service.transactions.entities.Transaction;
import service.transactions.mapper.MapperToTransactionResponse;
import service.transactions.model.TransactionType;
import service.transactions.repository.TransactionsRepository;
import service.transactions.service.StrategyTransactionsService;
import service.transactions.util.Utility;

@Service
public class ConcreteStrategyPayment implements StrategyTransactionsService {

    @Autowired
    private CreditClientConnector creditClientConnector;
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private MapperToTransactionResponse mapperToTransactionResponse;

    @Override
    public Mono<TransactionResponseDto> executedTransaction(Mono<TransactionRequestDto> transactionRequest) {
        return transactionRequest.flatMap(transactionRequestDto -> creditClientConnector.getCreditByProductId(transactionRequestDto.getProductId())
                .flatMap(creditServiceResponse -> {
                    UpdateCreditRequestDto creditRequestDto = UpdateCreditRequestDto.builder()
                            .amount(creditServiceResponse.getAmount() - transactionRequestDto.getAmount())
                            .interestRate(creditServiceResponse.getInterestRate())
                            .termMonths(creditServiceResponse.getTermMonths())
                            .build();
                    return creditClientConnector.updateCreditBalanceByProductId(creditServiceResponse.getId(), creditRequestDto)
                            .flatMap(creditServiceResponseDto -> {
                                Transaction transaction = Transaction.builder()
                                        .typeTransaction("PAYMENT")
                                        .amount(transactionRequestDto.getAmount())
                                        .dateTransaction(Utility.getDateTimeNow())
                                        .typeTransaction(creditServiceResponseDto.getId())
                                        .build();
                                return transactionsRepository.save(transaction)
                                        .map(mapperToTransactionResponse);
                            });
                }));
    }

    @Override
    public TransactionType getTransactionType() {
        return TransactionType.PAYMENT;
    }
}
