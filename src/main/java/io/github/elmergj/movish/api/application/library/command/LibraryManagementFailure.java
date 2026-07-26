package io.github.elmergj.movish.api.application.library.command;

import io.github.elmergj.movish.api.application.Result;

public sealed interface LibraryManagementFailure extends Result.FailureReason {

    record UserTitleAlreadyInLibrary(
            String userTitleId
    ) implements LibraryManagementFailure {
    }
}
