package service.transactions.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.transactions.client.CreditClientConnector;
import service.transactions.client.dto.AccountServiceResponseDto;
import service.transactions.client.dto.CreditServiceResponseDto;
import service.transactions.client.dto.UpdateCreditRequestDto;
import service.transactions.rest.HttpInterface;

@Service
@RequiredArgsConstructor
public class CreditClientImpl implements CreditClientConnector {

    private final HttpInterface httpInterface;

    @Value("${spring.config.client.credit.url}")
    private String url;

    @Override
    public Mono<CreditServiceResponseDto> getCreditByProductId(String productId) {
        return httpInterface.getHttpMono(url.concat("/").concat(productId), null);
    }

    @Override
    public Mono<CreditServiceResponseDto> updateCreditBalanceByProductId(String productId, UpdateCreditRequestDto updateCreditRequestDto) {
        return httpInterface.putHttp(
                url.concat("/").concat(productId),
                updateCreditRequestDto,
                CreditServiceResponseDto.class);
    }

    @Override
    public Flux<CreditServiceResponseDto> getCreditTransactionsByProductId(String productId) {
        return httpInterface.getHttpFlux(url.concat("/").concat(productId), null);
    }
}
