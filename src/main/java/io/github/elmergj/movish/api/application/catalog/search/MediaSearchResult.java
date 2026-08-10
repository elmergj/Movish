package io.github.elmergj.movish.api.application.catalog.search;

public sealed interface MediaSearchResult {

    record MediaDetailedResult(
    ) implements MediaSearchResult {
    }

    record MediaSummaryResult(
            String title,
            String thumbnail,
            Double Rating,
            String mediaType,
            String releaseDate
    ) implements MediaSearchResult {
    }

}
