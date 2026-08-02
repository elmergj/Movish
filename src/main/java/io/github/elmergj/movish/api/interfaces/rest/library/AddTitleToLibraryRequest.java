package io.github.elmergj.movish.api.interfaces.rest.library;

public record AddTitleToLibraryRequest(
        String mediaId,
        String mediaType
) {
}
