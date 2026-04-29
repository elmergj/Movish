package io.github.elmergj.movish.api.interfaces.rest.listing;

public record TitleAdditionResponse(
        String customListId,
        int totalElements,
        String successMessage
) {
}
