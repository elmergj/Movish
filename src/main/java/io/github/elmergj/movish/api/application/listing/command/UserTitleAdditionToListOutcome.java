package io.github.elmergj.movish.api.application.listing.command;

public record UserTitleAdditionToListOutcome(
        String customListId,
        String userTitleId,
        int totalElements
) {
}
