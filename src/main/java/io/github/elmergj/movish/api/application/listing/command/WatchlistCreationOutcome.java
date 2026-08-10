package io.github.elmergj.movish.api.application.listing.command;

public record WatchlistCreationOutcome(
        String customListId,
        String name,
        String dateCreated
) {
}
