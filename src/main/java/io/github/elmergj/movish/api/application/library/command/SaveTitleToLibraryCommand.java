package io.github.elmergj.movish.api.application.library.command;

public record SaveTitleToLibraryCommand(
        String externalTitleId,
        String mediaType,
        String userId
) {
}
