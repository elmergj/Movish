package io.github.elmergj.movish.api.interfaces.rest.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.NonNull;

@Getter
public class RestApiException extends RuntimeException {

    private final String reasonPhrase;
    private final @NonNull HttpStatusCode status;

    public RestApiException(String message, String reasonPhrase, @NonNull HttpStatusCode status) {
        super(message);
        this.reasonPhrase = reasonPhrase;
        this.status = status;
    }

    @NonNull
    public HttpStatusCode getStatus() {
        return status;
    }
}

