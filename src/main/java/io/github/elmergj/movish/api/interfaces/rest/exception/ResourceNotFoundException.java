package io.github.elmergj.movish.api.interfaces.rest.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RestApiException {

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
    }
}
