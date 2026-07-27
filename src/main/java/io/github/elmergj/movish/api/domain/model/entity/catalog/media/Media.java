
package io.github.elmergj.movish.api.domain.model.entity.catalog.media;

import java.time.LocalDate;
import java.util.List;

public record Media(
        MediaId id,
        List<String> externalIds,
        String name,
        MediaType mediaType,
        List<MediaAverageRating> mediaAverageRatings,
        MediaGenre[] genres,
        LocalDate releaseDate
) {
}
