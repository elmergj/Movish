package io.github.elmergj.movish.api.interfaces.rest.library;

public record UserTitleCreationResponse(
        String externalTitleId,
        String titleName,
        String trackingStatus,
        String dateAdded,
        double tmdbRating
) {
}
