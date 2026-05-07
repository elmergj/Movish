package io.github.elmergj.movish.api.application.listing.command;

public record UpdateCustomListNameCommand(
        String userId,
        String customListId,
        String newName
) {
}
