package io.github.elmergj.movish.api.interfaces.rest.listing;

public record ListDetailsResponse(
        String listId,
        String name,
        int totalElements
) {
}
