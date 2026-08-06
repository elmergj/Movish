package io.github.elmergj.movish.api.application.library.command;

import io.github.elmergj.movish.api.application.Result.FailureOutcome;

public sealed interface LibraryManagementFailure extends FailureOutcome {

    record TitleAlreadyInLibrary(
            String titleId
    ) implements LibraryManagementFailure {
    }

    record TitleFavoriteStatusAlreadyUpdated(
            String titleId
    ) implements LibraryManagementFailure {
    }

    record TitleTrackingStatusAlreadyUpdated(
            String titleId
    ) implements LibraryManagementFailure {
    }
}
