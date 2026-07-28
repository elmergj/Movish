package io.github.elmergj.movish.api.application.library.query;

public record TitleDetailsView(
        String titleId,
        String trackingStatus,
        String dateAdded,
        boolean isFavorite,
        TitleDetails titleDetails
) {
}
