package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * DTO for the /search/multi endpoint.
 * TECHNICAL CRITERION: This endpoint returns a mix of types (movie, tv, person).
 * A "Flexible Hybrid" approach is used to capture fields from both worlds
 * (Cinema and TV) in a single transport object.
 */
public record MultiSearchResponse(
        Integer page,
        List<MultiSearchResultDto> results,
        @JsonProperty("total_pages") Integer totalPages,
        @JsonProperty("total_results") Integer totalResults
) {

    /**
     * Hybrid DTO for multi-search results.
     * DESIGN CRITERION:
     * 1. 'mediaType': This is the discriminator field. YOU MUST read it first to determine
     * whether the object is 'movie' or 'tv'.
     * 2. Name duality: Includes 'title' (Movie) and 'name' (TV).
     * One of them will be null depending on the mediaType.
     * 3. Date duality: 'releaseDate' vs 'firstAirDate'.
     */
    public record MultiSearchResultDto(
            Long id,

            @JsonProperty("media_type")
            String mediaType, // "movie", "tv" o "person"

            // --- Campos comunes de Cine ---
            String title,
            @JsonProperty("original_title")
            String originalTitle,
            @JsonProperty("release_date")
            String releaseDate,
            Boolean video,

            // --- Campos comunes de TV ---
            String name,
            @JsonProperty("original_name")
            String originalName,
            @JsonProperty("first_air_date")
            String firstAirDate,
            @JsonProperty("origin_country")
            List<String> originCountry,

            // --- Campos compartidos (Metadata) ---
            String overview,
            @JsonProperty("poster_path")
            String posterPath,
            @JsonProperty("backdrop_path")
            String backdropPath,
            @JsonProperty("original_language")
            String originalLanguage,
            @JsonProperty("genre_ids")
            List<Integer> genreIds,
            Double popularity,
            @JsonProperty("vote_average")
            Double voteAverage,
            @JsonProperty("vote_count")
            Integer voteCount,
            Boolean adult
    ) {}
}