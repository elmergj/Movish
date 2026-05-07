package io.github.elmergj.movish.api.application.user;

public record RegisterUserCommand(
        String authId,
        String email,
        String name
) {}
