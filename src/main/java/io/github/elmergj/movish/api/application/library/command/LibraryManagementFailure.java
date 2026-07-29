package io.github.elmergj.movish.api.application.library.command;

import io.github.elmergj.movish.api.application.Result.FailureOutcome;

public sealed interface LibraryManagementFailure extends FailureOutcome {

    record TitleAlreadyInLibrary(
            String titleId
    ) implements LibraryManagementFailure {
    }

    
}
