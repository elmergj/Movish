package io.github.elmergj.movish.api.interfaces.rest.watchlist;

public record ListNameUpdateResponse(
        String listId,
        String name,
        int totalElements
) {
}
