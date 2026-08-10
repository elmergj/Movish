package io.github.elmergj.movish.api.application.listing.command;

public record RemoveTitleFromWatchlistCommand(
        String userId,
        String customListId,
        String titleId
) {
}
