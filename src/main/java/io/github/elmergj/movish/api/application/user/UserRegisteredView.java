package io.github.elmergj.movish.api.application.user;

public record UserRegisteredView(
        String email,
        String name,
        String username,
        String id
) {
}
