package service.transactions.client.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import service.transactions.exception.CustomHttpException;

@Component
public class HttpClientService {

    private final WebClient webClient;

    public HttpClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build(); // Se mantiene una única instancia de WebClient
    }

    /**
     * Método genérico para hacer peticiones GET a cualquier API externa con manejo de errores.
     * @param baseUrl URL base del servicio externo (ej. http://localhost:8081).
     * @param endpoint Ruta del endpoint (ej. /api/datos1).
     * @return Mono<String> con la respuesta del servicio.
     */
    public Mono<String> callServiceExternal(String baseUrl, String endpoint) {
        return webClient.mutate()
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri(endpoint)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response ->
                        response.bodyToMono(String.class).flatMap(body ->
                                Mono.error(new CustomHttpException("Error 4XX en " + baseUrl + endpoint, response.statusCode(), body))
                        )
                )
                .onStatus(HttpStatus::is5xxServerError, response ->
                        response.bodyToMono(String.class).flatMap(body ->
                                Mono.error(new CustomHttpException("Error 5XX en " + baseUrl + endpoint, response.statusCode(), body))
                        )
                )
                .bodyToMono(String.class)
                .doOnError(WebClientResponseException.class, error ->
                        System.err.println("Error en la petición HTTP: " + error.getMessage())
                )
                .doOnError(Throwable.class, error ->
                        System.err.println("Error desconocido en la petición: " + error.getMessage())
                );
    }
}
