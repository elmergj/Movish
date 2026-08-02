package io.github.elmergj.movish.api.interfaces.rest.library;

public record TitleCreationResponse(
        String titleId,
        String mediaId,
        String titleName,
        String dateAdded
) {
}
