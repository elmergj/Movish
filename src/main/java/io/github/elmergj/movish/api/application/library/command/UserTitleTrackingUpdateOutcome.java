package io.github.elmergj.movish.api.application.library.command;

public record UserTitleTrackingUpdateOutcome(
        String externalTitleId,
        String trackingStatus,
        String dateAdded,
        boolean isFavorite
) {
}
