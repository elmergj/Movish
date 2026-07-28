package io.github.elmergj.movish.api.application.library.command;

import io.github.elmergj.movish.api.application.Result;

public sealed interface LibraryManagementOutcome extends Result.SuccessOutcome{

    record TitleAdditionOutcome(
            String titleId,
            String titleName,
            String dateAdded
    ) implements LibraryManagementOutcome {
    }

    record TitleRemovalOutcome(
            String userTitleId
    ) implements LibraryManagementOutcome {
    }

    record TitleFavoriteOutcome(
            String externalTitleId,
            boolean isFavorite
    ) implements LibraryManagementOutcome {
    }

    record TitleTrackingUpdateOutcome(
            String externalTitleId,
            String trackingStatus,
            String dateAdded,
            boolean isFavorite
    ) implements LibraryManagementOutcome {
    }
}
