package io.github.elmergj.movish.api.interfaces.rest.library;

import java.util.Collection;

public record TitleCreationResponse(
        String titleId,
        Collection<MediaExternalIdPair> mediaExternalIds,
        String titleName,
        String dateAdded
) {
}
