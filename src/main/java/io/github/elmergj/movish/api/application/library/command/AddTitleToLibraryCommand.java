package io.github.elmergj.movish.api.application.library.command;

import io.github.elmergj.movish.api.application.library.MediaExternalReference;

import java.util.Collection;

public record AddTitleToLibraryCommand(
        Collection<MediaExternalReference> mediaExternalReferences,
        String mediaType,
        String userId
) {
}
