package io.github.elmergj.movish.api.application.catalog.search;

import io.github.elmergj.movish.api.application.Command;

public record SearchMediaQuery(
        String query,
        int page,
        int pageSize
) implements Command {
}
