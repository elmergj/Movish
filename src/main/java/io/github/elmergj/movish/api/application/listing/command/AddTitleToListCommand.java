package io.github.elmergj.movish.api.application.listing.command;

public record AddTitleToListCommand(
        String userId,
        String customListId,
        String titleId
) {
}
