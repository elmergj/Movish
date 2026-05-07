package io.github.elmergj.movish.api.application.catalog.command;

public record SearchTitleCommand(
        String query,
        int page,
        int pageSize
) {

}
