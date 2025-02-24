package service.transactions.client;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.transactions.client.dto.AccountServiceResponseDto;
import service.transactions.client.dto.RequestAccountDto;

public interface AccountClientConnector {
    Mono<AccountServiceResponseDto> getAccountByProductId(String productId);
    Mono<AccountServiceResponseDto> updateAccountBalanceByProductId(String productId, RequestAccountDto requestAccountDto);

    Flux<AccountServiceResponseDto> getAccountTransactionsByProductId(String productId);
}
