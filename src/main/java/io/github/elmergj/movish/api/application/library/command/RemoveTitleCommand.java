package io.github.elmergj.movish.api.application.library.command;

import io.github.elmergj.movish.api.application.Command;

public record RemoveTitleCommand(
        String userId,
        String titleId
) implements Command {
}
