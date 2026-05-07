package io.github.elmergj.movish.api.application.listing.query;

public record ListDetailsView(
        String listId,
        String name,
        int totalElements
) {
}
