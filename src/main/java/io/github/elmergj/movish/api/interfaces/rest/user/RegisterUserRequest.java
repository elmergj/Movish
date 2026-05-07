package io.github.elmergj.movish.api.interfaces.rest.user;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequest(
        @NotBlank String email,
        @NotBlank String name
) {
}
