package io.github.elmergj.movish.api.interfaces.rest.exception;

import io.github.elmergj.movish.api.domain.exception.DomainException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@Slf4j
@RestControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponseDTO> handleDomainException(DomainException domainEx, HttpServletRequest request) {
        log.info("Domain Exception: {}", domainEx.getMessage());
        RestApiException restEx = DomainExceptionMapper.map(domainEx);
        return ResponseEntity.status(restEx.getStatus())
                .body(buildErrorResponse(domainEx, restEx, request));
    }

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<ErrorResponseDTO> handleRestApiException(RestApiException restEx, HttpServletRequest request) {

        return ResponseEntity.status(restEx.getStatus()).body(buildErrorResponse(restEx, restEx, request));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex, HttpServletRequest request) {
        log.warn(ex.getMessage());
        RestApiException restEx = new BadRequestException("Invalid request body");

        return ResponseEntity.status(restEx.getStatus()).body(buildErrorResponse(restEx, restEx, request));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.warn(ex.getMessage()); // Add a more detailed message about what was not validated correctly.
        RestApiException restEx = new BadRequestException("Invalid request body");

        return ResponseEntity.status(restEx.getStatus()).body(buildErrorResponse(restEx, restEx, request));
    }

    private ErrorResponseDTO buildErrorResponse(
            RuntimeException messageException, RestApiException restApiException, HttpServletRequest request) {
        return ErrorResponseDTO.builder()
                .status(restApiException.getStatus().value())
                .httpMethod(request.getMethod())
                .error(restApiException.getReasonPhrase())
                .message(messageException.getMessage())
                .timestamp(LocalDate.now().toString())
                .path(request.getRequestURI())
                .build();
    }

    //Comment
}