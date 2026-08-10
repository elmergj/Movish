package io.github.elmergj.movish.api.application.listing.query;

public record WatchlistOverviewView(
        String listId,
        String name,
        int totalElements
) {
}
