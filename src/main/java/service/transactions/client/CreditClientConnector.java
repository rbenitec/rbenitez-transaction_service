package service.transactions.client;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.transactions.client.dto.AccountServiceResponseDto;
import service.transactions.client.dto.CreditServiceResponseDto;
import service.transactions.client.dto.RequestAccountDto;
import service.transactions.client.dto.UpdateCreditRequestDto;

public interface CreditClientConnector {
    Mono<CreditServiceResponseDto> getCreditByProductId(String productId);
    Mono<CreditServiceResponseDto> updateCreditBalanceByProductId(String productId, UpdateCreditRequestDto updateCreditRequestDto);

    Flux<CreditServiceResponseDto> getCreditTransactionsByProductId(String productId);
}
