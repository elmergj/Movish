package io.github.elmergj.movish.api.application.library.command;

public record AddTitleToLibraryCommand(
        String mediaId,
        String mediaType,
        String userId
) {
}
