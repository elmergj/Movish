package io.github.elmergj.movish.api.application.library.command;

import io.github.elmergj.movish.api.application.Command;

public record DeleteUserTitleCommand(
        String userId,
        String userTitleId
) implements Command {
}
