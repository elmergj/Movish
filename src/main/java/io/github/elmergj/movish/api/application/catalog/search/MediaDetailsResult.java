package io.github.elmergj.movish.api.application.catalog.search;

import io.github.elmergj.movish.api.domain.model.entity.library.MediaType;

import java.time.LocalDate;

public record MediaDetailsResult(
        String externalMediaId,
        String name,
        LocalDate releaseDate,
        MediaType mediaType,
        Double tmdbMediaRating) {
}
