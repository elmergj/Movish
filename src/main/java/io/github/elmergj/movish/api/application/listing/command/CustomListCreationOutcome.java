package io.github.elmergj.movish.api.application.listing.command;

public record CustomListCreationOutcome(
        String customListId,
        String name,
        String dateCreated
) {
}
