package service.transactions.service;

import reactor.core.publisher.Mono;
import service.transactions.dto.TransactionRequestDto;
import service.transactions.dto.TransactionResponseDto;

public class ContextTransaction {

    private StrategyTransactionsService strategyTransactionsService;

    public void setStrategyTransactionsService(StrategyTransactionsService strategyTransactionsService) {
        this.strategyTransactionsService = strategyTransactionsService;
    }

    public Mono<TransactionResponseDto> executedStrategy(Mono<TransactionRequestDto> transactionRequest){
        return strategyTransactionsService.executedTransaction(transactionRequest);
    }
}
