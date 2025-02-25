package service.transactions.service;

import reactor.core.publisher.Mono;
import service.transactions.dto.TransactionRequestDto;
import service.transactions.dto.TransactionResponseDto;
import service.transactions.model.TransactionType;

public interface StrategyTransactionsService {
    Mono<TransactionResponseDto> executedTransaction(Mono<TransactionRequestDto> transactionRequest);
    TransactionType getTransactionType();
}
