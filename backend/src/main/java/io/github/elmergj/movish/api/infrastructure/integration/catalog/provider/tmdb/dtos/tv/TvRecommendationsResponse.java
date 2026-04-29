package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * DTO for TV series recommendations response.
 * Follows the standard TMDB pagination pattern.
 */
public record TvRecommendationsResponse(
        @JsonProperty("page") Integer page,
        @JsonProperty("results") List<TvRecommendation> results,
        @JsonProperty("total_pages") Integer totalPages,
        @JsonProperty("total_results") Integer totalResults
) {
    /**
     * REUSE: This record represents a simplified version of a TV series (typical in listings).
     * It is kept nested here to represent exactly what the recommendations API
     * returns, avoiding confusion with the full series detail.
     */
    public record TvRecommendation(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("original_name") String originalName,
            @JsonProperty("overview") String overview,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("adult") boolean adult,
            @JsonProperty("backdrop_path") String backdropPath,
            @JsonProperty("poster_path") String posterPath,
            @JsonProperty("original_language") String originalLanguage,
            @JsonProperty("popularity") Double popularity,
            @JsonProperty("first_air_date") String firstAirDate,
            @JsonProperty("vote_average") Double voteAverage,
            @JsonProperty("vote_count") Integer voteCount,
            @JsonProperty("origin_country") List<String> originCountry,
            @JsonProperty("genre_ids") List<Integer> genreIds
    ) {}
}
