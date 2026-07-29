package io.github.elmergj.movish.api.application.listing.command;

public record TitleAdditionToListOutcome(
        String customListId,
        String titleId,
        int totalElements
) {
}
