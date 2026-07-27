package io.github.elmergj.movish.api.domain.model.entity.catalog.search;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaType;

import java.time.LocalDate;

public record MediaDetailsResult(
        String externalMediaId,
        String name,
        LocalDate releaseDate,
        MediaType mediaType,
        Double tmdbMediaRating) {
}
