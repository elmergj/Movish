package io.github.elmergj.movish.api.application.library.command;

import io.github.elmergj.movish.api.application.Command;

public record UpdateTitleTrackingStatusCommand(
        String userId,
        String userTitleId,
        String trackingStatus
) implements Command {
}
