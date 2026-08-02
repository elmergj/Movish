package io.github.elmergj.movish.api.application.catalog.search;

public record MediaSummaryResult(
        String title,
        String thumbnail,
        Double Rating, // Optimize: To define how this DTO/Object is properly used by all layers, and the correct object used.
        String mediaType,
        String releaseDate
) implements SearchResult {
}
