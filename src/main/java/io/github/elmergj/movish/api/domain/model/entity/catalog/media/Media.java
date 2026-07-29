
package io.github.elmergj.movish.api.domain.model.entity.catalog.media;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record Media(
        MediaId id,
        Set<MediaExternalId> externalIds,
        String name,
        MediaType mediaType,
        List<MediaAverageRating> mediaAverageRatings,
        MediaGenre[] genres,
        LocalDate releaseDate
) {
}
