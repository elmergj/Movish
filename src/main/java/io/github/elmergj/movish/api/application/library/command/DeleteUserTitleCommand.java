package io.github.elmergj.movish.api.application.library.command;

public record DeleteUserTitleCommand(
        String userId,
        String userTitleId
) {
}
