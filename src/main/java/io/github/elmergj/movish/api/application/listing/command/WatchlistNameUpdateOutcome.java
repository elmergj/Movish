package io.github.elmergj.movish.api.application.listing.command;

public record WatchlistNameUpdateOutcome(
        String listId,
        String name,
        int totalElements
) {
}
