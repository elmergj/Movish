package io.github.elmergj.movish.api.interfaces.rest.library;

public record AddTitleToLibraryRequest(
        String externalTitleId,
        String mediaType,
        String providerName
) {
}
