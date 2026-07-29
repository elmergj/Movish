package io.github.elmergj.movish.api.application.library.command;

import io.github.elmergj.movish.api.application.Result;
import io.github.elmergj.movish.api.application.library.MediaExternalReference;

import java.util.Collection;

public sealed interface LibraryManagementOutcome extends Result.SuccessOutcome{

    record TitleAdditionOutcome(
            String titleId,
            Collection<MediaExternalReference> externalIdReferences,
            String titleName,
            String dateAdded
    ) implements LibraryManagementOutcome {
    }

    record TitleRemovalOutcome(
            String titleId
    ) implements LibraryManagementOutcome {
    }

    record TitleFavoriteOutcome(
            String mediaExternalId,
            boolean isFavorite
    ) implements LibraryManagementOutcome {
    }

    record TitleTrackingUpdateOutcome(
            String mediaExternalId,
            String trackingStatus,
            String dateAdded,
            boolean isFavorite
    ) implements LibraryManagementOutcome {
    }


}
