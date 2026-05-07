package io.github.elmergj.movish.api.domain.model.entity.catalog.search;

public record TitleSummaryResult(
        String providerId,
        String titleName,
        String thumbnail,
        Double Rating, // Optimize: To define how this DTO/Object is properly used by all layers, and the correct object used.
        String mediaType)
        implements SearchResult {
}
