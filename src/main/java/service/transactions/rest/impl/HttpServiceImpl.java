package service.transactions.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.transactions.exception.ApiRestExternalException;
import service.transactions.rest.HttpInterface;

@Service
public class HttpServiceImpl implements HttpInterface {

    private final WebClient webClient;

    @Autowired
    public HttpServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public <T> Mono<T> getHttpMono(String url, Class<T> responseType) {
        return webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path(url);
                    return uriBuilder.build();
                })
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new ApiRestExternalException(e.getStatusCode(), e.getMessage(), e.getResponseBodyAsString(), url)));
    }

    @Override
    public <T> Flux<T> getHttpFlux(String url, Class<T> responseType) {
        return webClient.get().uri(uriBuilder -> {
                    uriBuilder.path(url);
                    return uriBuilder.build();
                })
                .retrieve()
                .bodyToFlux(responseType)
                .onErrorResume(WebClientResponseException.class, e -> Flux.error(new ApiRestExternalException(e.getStatusCode(), e.getMessage(), e.getResponseBodyAsString(), url)));
    }

    @Override
    public <T, R> Mono<T> postHttp(String url, R requestBody, Class<T> responseType) {
        return webClient.post()
                .uri(uriBuilder -> {
                    uriBuilder.path(url);
                    return uriBuilder.build();
                })
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new ApiRestExternalException(e.getStatusCode(), e.getMessage(), e.getResponseBodyAsString(), url)));
    }

    @Override
    public <T, R> Mono<T> putHttp(String url, R requestBody, Class<T> responseType) {
        return webClient.put()
                .uri(uriBuilder -> {
                    uriBuilder.path(url);
                    return uriBuilder.build();
                })
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new ApiRestExternalException(e.getStatusCode(), e.getMessage(), e.getResponseBodyAsString(), url)));
    }

    @Override
    public <T, R> Mono<T> patchHttp(String url, R requestBody, Class<T> responseType) {
        return webClient.patch()
                .uri(uriBuilder -> {
                    uriBuilder.path(url);
                    return uriBuilder.build();
                })
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new ApiRestExternalException(e.getStatusCode(), e.getMessage(), e.getResponseBodyAsString(), url)));
    }

    @Override
    public Mono<Void> deleteHttp(String url) {
        return webClient.delete()
                .uri(uriBuilder -> {
                    uriBuilder.path(url);
                    return uriBuilder.build();
                })
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new ApiRestExternalException(e.getStatusCode(), e.getMessage(), e.getResponseBodyAsString(), url)));
    }

    /*

    private final WebClient webClient;

    @Autowired
    public HttpServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    public <T> Mono<T> getHttp(String url, Class<T> responseType) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(responseType);
    }


    public <T, R> Mono<T> postHttp(String url, R requestBody, Class<T> responseType) {
        return webClient.post()
                .uri(url)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T, R> Mono<T> putHttp(String url, R requestBody, Class<T> responseType) {
        return webClient.put()
                .uri(url)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(responseType);
    }

    public Mono<Void> deleteHttp(String url) {
        return webClient.delete()
                .uri(url)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public <T, R> Mono<T> patchHttp(String url, R requestBody, Class<T> responseType) {
        return webClient.patch()
                .uri(url)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(responseType);
    }

     */

    /*
    private final WebClient webClient;

    @Autowired
    public HttpServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    public <T> Mono<T> get(String url, Map<String, String> headers, Map<String, String> queryParams, Class<T> responseType) {
        WebClient.RequestHeadersUriSpec<?> request = webClient.get().uri(uriBuilder -> {
            uriBuilder.path(url);
            queryParams.forEach(uriBuilder::queryParam);
            return uriBuilder.build();
        });

        headers.forEach(request::header);

        return request.retrieve().bodyToMono(responseType);
    }

    public <T, R> Mono<T> post(String url, R requestBody, Map<String, String> headers, Map<String, String> queryParams, Class<T> responseType) {
        WebClient.RequestBodyUriSpec request = webClient.post().uri(uriBuilder -> {
            uriBuilder.path(url);
            queryParams.forEach(uriBuilder::queryParam);
            return uriBuilder.build();
        });

        headers.forEach(request::header);

        return request.bodyValue(requestBody).retrieve().bodyToMono(responseType);
    }

    public <T, R> Mono<T> put(String url, R requestBody, Map<String, String> headers, Map<String, String> queryParams, Class<T> responseType) {
        WebClient.RequestBodyUriSpec request = webClient.put().uri(uriBuilder -> {
            uriBuilder.path(url);
            queryParams.forEach(uriBuilder::queryParam);
            return uriBuilder.build();
        });

        headers.forEach(request::header);

        return request.bodyValue(requestBody).retrieve().bodyToMono(responseType);
    }

    public <T, R> Mono<T> patch(String url, R requestBody, Map<String, String> headers, Map<String, String> queryParams, Class<T> responseType) {
        WebClient.RequestBodyUriSpec request = webClient.patch().uri(uriBuilder -> {
            uriBuilder.path(url);
            queryParams.forEach(uriBuilder::queryParam);
            return uriBuilder.build();
        });

        headers.forEach(request::header);

        return request.bodyValue(requestBody).retrieve().bodyToMono(responseType);
    }

    public Mono<Void> delete(String url, Map<String, String> headers, Map<String, String> queryParams) {
        WebClient.RequestHeadersUriSpec<?> request = webClient.delete().uri(uriBuilder -> {
            uriBuilder.path(url);
            queryParams.forEach(uriBuilder::queryParam);
            return uriBuilder.build();
        });

        headers.forEach(request::header);

        return request.retrieve().bodyToMono(Void.class);
    }
     */

    /*
    private final WebClient webClient;

    @Autowired
    public HttpServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public <T> Mono<T> get(String url, Map<String, String> headers, Map<String, String> queryParams, Class<T> responseType) {
        return webClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path(url);
                    queryParams.forEach(uriBuilder::queryParam);
                    return uriBuilder.build();
                })
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new CustomException(e.getStatusCode(), e.getMessage(), e.getResponseBodyAsString(), url)));
    }

    @Override
    public <T, R> Mono<T> post(String url, R requestBody, Map<String, String> headers, Map<String, String> queryParams, Class<T> responseType) {
        return webClient.post()
                .uri(uriBuilder -> {
                    uriBuilder.path(url);
                    queryParams.forEach(uriBuilder::queryParam);
                    return uriBuilder.build();
                })
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new CustomException(e.getStatusCode(), e.getMessage(), e.getResponseBodyAsString(), url)));
    }

    @Override
    public <T, R> Mono<T> put(String url, R requestBody, Map<String, String> headers, Map<String, String> queryParams, Class<T> responseType) {
        return webClient.put()
                .uri(uriBuilder -> {
                    uriBuilder.path(url);
                    queryParams.forEach(uriBuilder::queryParam);
                    return uriBuilder.build();
                })
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new CustomException(e.getStatusCode(), e.getMessage(), e.getResponseBodyAsString(), url)));
    }

    @Override
    public <T, R> Mono<T> patch(String url, R requestBody, Map<String, String> headers, Map<String, String> queryParams, Class<T> responseType) {
        return webClient.patch()
                .uri(uriBuilder -> {
                    uriBuilder.path(url);
                    queryParams.forEach(uriBuilder::queryParam);
                    return uriBuilder.build();
                })
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new CustomException(e.getStatusCode(), e.getMessage(), e.getResponseBodyAsString(), url)));
    }

    @Override
    public Mono<Void> delete(String url, Map<String, String> headers, Map<String, String> queryParams) {
        return webClient.delete()
                .uri(uriBuilder -> {
                    uriBuilder.path(url);
                    queryParams.forEach(uriBuilder::queryParam);
                    return uriBuilder.build();
                })
                .headers(httpHeaders -> headers.forEach(httpHeaders::add))
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new CustomException(e.getStatusCode(), e.getMessage(), e.getResponseBodyAsString(), url)));
    }
     */
}
