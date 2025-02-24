package service.transactions.rest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HttpInterface {
    <T> Mono<T> getHttpMono(String url, Class<T> responseType);
    <T> Flux<T> getHttpFlux(String url, Class<T> responseType);

    <T, R> Mono<T> postHttp(String url, R requestBody, Class<T> responseType);

    <T, R> Mono<T> putHttp(String url, R requestBody, Class<T> responseType);

    <T, R> Mono<T> patchHttp(String url, R requestBody, Class<T> responseType);

    Mono<Void> deleteHttp(String url);
}
