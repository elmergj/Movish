package io.github.elmergj.movish.api.interfaces.rest.watchlist;

public record ListDetailsResponse(
        String listId,
        String name,
        int totalElements
) {
}
