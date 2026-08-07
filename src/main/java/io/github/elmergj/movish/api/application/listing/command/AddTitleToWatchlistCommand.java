package io.github.elmergj.movish.api.application.listing.command;

public record AddTitleToWatchlistCommand(
        String userId,
        String customListId,
        String titleId
) {
}
