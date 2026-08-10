package io.github.elmergj.movish.api.application.listing.command;

public record UpdateWatchlistNameCommand(
        String userId,
        String customListId,
        String newName
) {
}
