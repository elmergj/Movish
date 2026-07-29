package io.github.elmergj.movish.api.application.listing.command;

public record TitleRemovalFromListOutcome(
        String customListId,
        String titleId,
        int totalElements
) {
}
