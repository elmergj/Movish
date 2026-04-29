package io.github.elmergj.movish.api.application.user;

public record UpdateUserProfileCommand(
        String username,
        String name,
        String email
) {
}
