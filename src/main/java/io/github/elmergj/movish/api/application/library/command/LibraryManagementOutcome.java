package io.github.elmergj.movish.api.application.library.command;

import io.github.elmergj.movish.api.application.Result;

public sealed interface LibraryManagementOutcome extends Result.SuccessOutcome{

    record TitleAdditionOutcome(
            String titleId,
            String mediaId,
            String titleName,
            String dateAdded
    ) implements LibraryManagementOutcome {
    }

    record TitleRemovalOutcome(
            String titleId
    ) implements LibraryManagementOutcome {
    }

    record TitleFavoriteOutcome(
            String titleId,
            boolean isFavorite
    ) implements LibraryManagementOutcome {
    }

    record TitleTrackingUpdateOutcome(
            String titleId,
            String trackingStatus,
            String dateAdded,
            boolean isFavorite
    ) implements LibraryManagementOutcome {
    }
}
