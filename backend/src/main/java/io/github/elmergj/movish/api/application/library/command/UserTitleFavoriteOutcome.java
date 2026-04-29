package io.github.elmergj.movish.api.application.library.command;

public record UserTitleFavoriteOutcome(
        String externalTitleId,
        boolean isFavorite
) {
}
