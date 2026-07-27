package io.github.elmergj.movish.api.application.library.command;

import io.github.elmergj.movish.api.application.Command;

public record UpdateTitleFavoriteStatusCommand(
        String userTitleId,
        String userId,
        boolean favorite
) implements Command {
}
