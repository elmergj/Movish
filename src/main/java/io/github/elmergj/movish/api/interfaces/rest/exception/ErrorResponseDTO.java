package io.github.elmergj.movish.api.interfaces.rest.exception;

import lombok.Builder;

@Builder
public record ErrorResponseDTO(
        int status,
        String httpMethod,
        String error,
        String message,
        String timestamp,
        String path)
{}
