package io.github.elmergj.movish.api.interfaces.rest.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends RestApiException {

    public InternalServerErrorException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
