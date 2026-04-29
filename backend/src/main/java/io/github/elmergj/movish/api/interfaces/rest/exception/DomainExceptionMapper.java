package io.github.elmergj.movish.api.interfaces.rest.exception;

import io.github.elmergj.movish.api.domain.exception.DomainException;
import io.github.elmergj.movish.api.domain.exception.EntityNotFoundException;
import io.github.elmergj.movish.api.domain.exception.ValidationException;


public final class DomainExceptionMapper {

    public static RestApiException map(DomainException exception) {
        if (exception instanceof EntityNotFoundException){
            return new ResourceNotFoundException(exception.getMessage());
        }
        if (exception instanceof ValidationException){
            return new BadRequestException(exception.getMessage());
        }

        return new InternalServerErrorException(exception.getMessage());
    }
}
