package io.github.elmergj.movish.api.interfaces.rest.catalog;

public record MediaSearchQuery(
        String query,
        int page,
        int size
) {
}
