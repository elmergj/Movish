package io.github.elmergj.movish.api.domain.model.entity.catalog.search;

import io.github.elmergj.movish.api.domain.model.entity.catalog.title.MediaType;

import java.time.LocalDate;

public record TitleDetailsResult(
        String externalTitleId,
        String name,
        LocalDate releaseDate,
        MediaType mediaType,
        Double tmdbTitleRating) {
}
