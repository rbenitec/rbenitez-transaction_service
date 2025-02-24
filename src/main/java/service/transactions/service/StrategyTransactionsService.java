package service.transactions.service;

import reactor.core.publisher.Mono;
import service.transactions.dto.TransactionRequestDto;
import service.transactions.dto.TransactionResponseDto;

public interface StrategyTransactionsService {
    Mono<TransactionResponseDto> executedTransaction(Mono<TransactionRequestDto> transactionRequest);
//    Mono<TransactionResponseDto> withdrawalTransaction(Mono<TransactionRequestDto> transactionRequest);
//    Mono<TransactionResponseDto> paymentTransaction(Mono<TransactionRequestDto> transactionRequest);

}
