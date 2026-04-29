package io.github.elmergj.movish.api.interfaces.rest.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RestApiException {

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST);
    }
}
