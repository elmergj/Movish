package io.github.elmergj.movish.api.interfaces.rest.catalog;

public record TitleSearchRequest(
        String query,
        int page,
        int size
) {
}
