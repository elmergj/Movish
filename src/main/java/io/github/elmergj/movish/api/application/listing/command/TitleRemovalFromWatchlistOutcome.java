package io.github.elmergj.movish.api.application.listing.command;

public record TitleRemovalFromWatchlistOutcome(
        String customListId,
        String titleId,
        int totalElements
) {
}
