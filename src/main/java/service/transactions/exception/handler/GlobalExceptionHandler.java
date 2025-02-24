package service.transactions.exception.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;
import service.transactions.dto.ErrorDto;
import service.transactions.exception.ApiRestExternalException;
import service.transactions.exception.BusinessException;
import service.transactions.exception.CustomHttpException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomHttpException.class)
    public ResponseEntity<Map<String, Object>> handlerErrorHttp(CustomHttpException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", ex.getMessage());
        response.put("status", ex.getStatus().value());
        response.put("body", ex.getResponseBody());

        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handlerErrorGenerals(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Error interno del servidor");
        response.put("message", ex.getMessage());

        return ResponseEntity.status(500).body(response);
    }

    @ExceptionHandler(ApiRestExternalException.class)
    public Mono<ResponseEntity<ErrorDto>> handleCustomException(ApiRestExternalException ex) {
        ErrorDto errorDto = new ErrorDto(
                ex.getStatus().value(),
                ex.getMessage(),
                ex.getDetail(),
                ex.getUrl()
        );
        return Mono.just(ResponseEntity.status(ex.getStatus()).body(errorDto));
    }

    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<ErrorDto>> businessException(BusinessException ex) {
        ErrorDto errorDto = new ErrorDto(
                HttpStatus.PARTIAL_CONTENT.value(),
                ex.getOperation(),
                ex.getMessage(),
                null
        );
        return Mono.just(ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(errorDto));
    }
}
