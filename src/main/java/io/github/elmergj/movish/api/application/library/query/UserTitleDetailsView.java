package io.github.elmergj.movish.api.application.library.query;

public record UserTitleDetailsView(
        String userTitleId,
        String trackingStatus,
        String dateAdded,
        boolean isFavorite,
        TitleDetails titleDetails
) {
}
