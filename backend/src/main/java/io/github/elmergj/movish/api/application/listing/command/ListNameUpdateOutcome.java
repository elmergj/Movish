package io.github.elmergj.movish.api.application.listing.command;

public record ListNameUpdateOutcome(
        String listId,
        String name,
        int totalElements
) {
}
