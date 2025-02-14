package service.transactions.exception;

import org.springframework.http.HttpStatus;

public class CustomHttpException extends RuntimeException {
    private final HttpStatus status;
    private final String responseBody;

    public CustomHttpException(String message, HttpStatus status, String responseBody) {
        super(message);
        this.status = status;
        this.responseBody = responseBody;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
