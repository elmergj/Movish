package io.github.elmergj.movish.api.application.library.command;

public record UserTitleCreationOutcome(
        String id,
        String titleName,
        //Ignore String imdbId,
        String tmdbId,
        //Ignore  double tmdbRating,
        double tmdbRating,
        String TrackingStatus,
        String dateAdded
) {
}
