package io.github.elmergj.movish.api.application.catalog;

public record MediaDetails(
        String mediaId,
        String name,
        String releaseDate,
        String mediaType,
        Double tmdbMediaRating) {
}
