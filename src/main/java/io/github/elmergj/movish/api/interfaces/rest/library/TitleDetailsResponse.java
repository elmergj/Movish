package io.github.elmergj.movish.api.interfaces.rest.library;

public record TitleDetailsResponse(
        String titleId,
        String trackingStatus,
        String dateAdded,
        boolean isFavorite,
        TitleDetailsContent titleDetailsContent
) {
    public record TitleDetailsContent(
            String mediaId,
            String name,
            String releaseDate
    ) {
    }

}
