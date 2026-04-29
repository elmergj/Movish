package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Root DTO for the TMDB recommendations response.
 * A "wrapper" design is used to capture the pagination metadata
 * provided by the external API, wrapping the actual list of domains.
 */
public record MovieRecommendationsResponse(

        Integer page,

        List<MovieRecommendationResultDto> results,

        @JsonProperty("total_pages")
        Integer totalPages,

        @JsonProperty("total_results")
        Integer totalResults
) {

    /**
     * Nested DTO representing the technical details of each recommendation.
     * DESIGN CRITERION:
     * 1. Includes social audit fields (votes) and popularity to enable
     * relevance filtering in the application layer.
     * 2. 'mediaType' is critical for logic dispatch in polymorphic systems (Movies vs TV).
     * 3. 'genreIds' is kept as a list of Integers to be processed by the
     * system's genre normalization service.
     */
    public record MovieRecommendationResultDto(

            Long id,

            String title,

            @JsonProperty("original_title")
            String originalTitle,

            // Plot summary; useful for text analysis or keywords
            String overview,

            @JsonProperty("poster_path")
            String posterPath,

            @JsonProperty("backdrop_path")
            String backdropPath,

            @JsonProperty("media_type")
            String mediaType,

            // Formato esperado: YYYY-MM-DD
            @JsonProperty("release_date")
            String releaseDate,

            @JsonProperty("genre_ids")
            List<Integer> genreIds,

            @JsonProperty("vote_average")
            Double voteAverage,

            @JsonProperty("vote_count")
            Integer voteCount,

            Boolean adult,

            Double popularity
    ) {}
}