package io.github.elmergj.movish.api.interfaces.rest.watchlist;

public record WatchlistOverviewResponse(
        String listId,
        String name,
        int totalElements
) {
}
