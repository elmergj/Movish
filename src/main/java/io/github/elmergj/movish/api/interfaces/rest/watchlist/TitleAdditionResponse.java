package io.github.elmergj.movish.api.interfaces.rest.watchlist;

public record TitleAdditionResponse(
        String customListId,
        int totalElements,
        String successMessage
) {
}
