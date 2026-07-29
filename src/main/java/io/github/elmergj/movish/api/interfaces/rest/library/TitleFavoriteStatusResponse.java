package io.github.elmergj.movish.api.interfaces.rest.library;

public record TitleFavoriteStatusResponse(
        String mediaExternalId,
        boolean isFavorite
) {
}
