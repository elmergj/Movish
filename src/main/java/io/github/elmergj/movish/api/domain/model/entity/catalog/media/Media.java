package io.github.elmergj.movish.api.domain.model.entity.catalog.media;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record Media(
        String id, // Previously MediaId
        Set<MediaExternalId> externalIds,
        String name,
        String mediaType, // Previously MediaType
        List<MediaAverageRating> mediaAverageRatings,
        MediaGenre[] genres,
        LocalDate releaseDate
) {
}
