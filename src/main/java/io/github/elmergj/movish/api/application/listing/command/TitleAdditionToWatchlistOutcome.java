package io.github.elmergj.movish.api.application.listing.command;

public record TitleAdditionToWatchlistOutcome(
        String customListId,
        String titleId,
        int totalElements
) {
}
