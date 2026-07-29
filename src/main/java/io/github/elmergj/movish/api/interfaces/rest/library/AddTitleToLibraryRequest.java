package io.github.elmergj.movish.api.interfaces.rest.library;

import java.util.Collection;

public record AddTitleToLibraryRequest(
        Collection<MediaExternalIdPair> mediaExternalIdPairs,
        String mediaType
) {
}
