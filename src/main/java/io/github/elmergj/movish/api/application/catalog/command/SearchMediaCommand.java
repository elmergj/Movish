package io.github.elmergj.movish.api.application.catalog.command;

import io.github.elmergj.movish.api.application.Command;

public record SearchMediaCommand(
        String query,
        int page,
        int pageSize
) implements Command {
}
