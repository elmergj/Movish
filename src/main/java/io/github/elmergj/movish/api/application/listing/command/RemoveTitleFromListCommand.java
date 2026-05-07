package io.github.elmergj.movish.api.application.listing.command;

public record RemoveTitleFromListCommand(
        String userId,
        String customListId,
        String titleId
) {
}
