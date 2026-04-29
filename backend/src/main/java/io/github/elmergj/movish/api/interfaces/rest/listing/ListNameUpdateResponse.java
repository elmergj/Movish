package io.github.elmergj.movish.api.interfaces.rest.listing;

public record ListNameUpdateResponse(
        String listId,
        String name,
        int totalElements
) {
}
