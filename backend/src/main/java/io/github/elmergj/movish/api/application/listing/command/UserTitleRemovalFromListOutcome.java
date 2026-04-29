package io.github.elmergj.movish.api.application.listing.command;

public record UserTitleRemovalFromListOutcome(
        String customListId,
        String userTitleId,
        int totalElements
) {
}
