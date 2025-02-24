package service.transactions.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.transactions.client.AccountClientConnector;
import service.transactions.client.dto.AccountServiceResponseDto;
import service.transactions.client.dto.RequestAccountDto;
import service.transactions.rest.HttpInterface;


@Service
@RequiredArgsConstructor
public class AccountClientImpl implements AccountClientConnector {

    private final HttpInterface httpInterface;

    @Value("${spring.config.client.account.url}")
    private String url;

    @Override
    public Mono<AccountServiceResponseDto> getAccountByProductId(String productId) {
        return httpInterface.getHttpMono(
                url.concat("/").concat(productId),
                null);
    }

    @Override
    public Mono<AccountServiceResponseDto> updateAccountBalanceByProductId(String productId, RequestAccountDto requestAccountDto) {
        return httpInterface.putHttp(
                url.concat("/").concat(productId),
                requestAccountDto,
                AccountServiceResponseDto.class);
    }

    @Override
    public Flux<AccountServiceResponseDto> getAccountTransactionsByProductId(String productId) {
        return httpInterface.getHttpFlux(url.concat("/").concat(productId), null);
    }
}
