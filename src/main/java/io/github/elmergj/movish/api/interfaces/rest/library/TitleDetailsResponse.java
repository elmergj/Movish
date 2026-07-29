package io.github.elmergj.movish.api.interfaces.rest.library;

import io.github.elmergj.movish.api.application.library.query.TitleDetails;

public record TitleDetailsResponse(
        String titleId,
        String trackingStatus,
        String dateAdded,
        boolean isFavorite,
        TitleDetails titleDetails
) {
}
