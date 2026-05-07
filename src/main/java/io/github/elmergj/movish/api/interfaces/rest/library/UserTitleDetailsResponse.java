package io.github.elmergj.movish.api.interfaces.rest.library;

import io.github.elmergj.movish.api.application.library.query.TitleDetails;

public record UserTitleDetailsResponse(
        String titleId,
        String trackingStatus,
        String dateAdded,
        boolean isFavorite,
        TitleDetails titleDetails
) {
}
