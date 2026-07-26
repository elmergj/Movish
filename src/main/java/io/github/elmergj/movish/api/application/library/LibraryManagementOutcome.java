package io.github.elmergj.movish.api.application.library;

import io.github.elmergj.movish.api.application.Result;

public sealed interface LibraryManagementOutcome extends Result.SuccessOutcome{

    record UserTitleCreationOutcome(
            String id,
            String titleName,
            //Ignore String imdbId,
            String tmdbId,
            //Ignore  double tmdbRating,
            double tmdbRating,
            String TrackingStatus,
            String dateAdded
    ) implements LibraryManagementOutcome {
    }

    record UserTitleDeletionOutcome(
            String userTitleId
    ) implements LibraryManagementOutcome {
    }

    record UserTitleFavoriteOutcome(
            String externalTitleId,
            boolean isFavorite
    ) implements LibraryManagementOutcome {
    }

    record UserTitleTrackingUpdateOutcome(
            String externalTitleId,
            String trackingStatus,
            String dateAdded,
            boolean isFavorite
    ) implements LibraryManagementOutcome {
    }
}
