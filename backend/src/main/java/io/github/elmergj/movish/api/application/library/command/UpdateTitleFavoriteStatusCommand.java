package io.github.elmergj.movish.api.application.library.command;

public record UpdateTitleFavoriteStatusCommand(
        String userTitleId,
        String userId,
        boolean favorite
) {
}
