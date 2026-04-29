package io.github.elmergj.movish.api.application.library.command;

public record UpdateTitleTrackingStatusCommand(
        String userId,
        String userTitleId,
        String trackingStatus
) {
}
