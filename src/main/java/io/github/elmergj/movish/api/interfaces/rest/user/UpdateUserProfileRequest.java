package io.github.elmergj.movish.api.interfaces.rest.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserProfileRequest(
        @NotBlank String username,
        @NotBlank String name,
        @NotBlank String email) {
}
