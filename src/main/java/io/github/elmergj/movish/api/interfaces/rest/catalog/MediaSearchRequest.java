package io.github.elmergj.movish.api.interfaces.rest.catalog;

public record MediaSearchRequest(
        String query,
        int page,
        int size
) {
}
