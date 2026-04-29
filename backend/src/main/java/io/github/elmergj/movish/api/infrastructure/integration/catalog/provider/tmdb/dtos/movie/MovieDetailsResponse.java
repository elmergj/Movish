package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * DTO for the full details of a movie.
 * Design note: The complete TMDB structure is mapped, but in the Mapper
 * we will decide which fields to send to the Domain to keep it clean.
 */
public record MovieDetailsResponse(
        Long id,
        String title,

        @JsonProperty("original_title")
        String originalTitle,

        String overview,

        @JsonProperty("release_date")
        String releaseDate, // Note: It is recommended to map as String and convert to LocalDate in the Mapper

        @JsonProperty("poster_path")
        String posterPath,

        @JsonProperty("backdrop_path")
        String backdropPath,

        List<GenreDto> genres,

        Integer runtime,

        @JsonProperty("vote_average")
        Double voteAverage,

        @JsonProperty("vote_count")
        Integer voteCount,

        @JsonProperty("belongs_to_collection")
        CollectionSummaryDto belongsToCollection,

        @JsonProperty("production_companies")
        List<ProductionCompanyDto> productionCompanies,

        Long budget,
        Long revenue,
        String status,
        String tagline,

        @JsonProperty("imdb_id")
        String imdbId
) {
    // Internal DTOs for nested structures
    public record GenreDto(Integer id, String name) {}

    public record CollectionSummaryDto(
            Long id,
            String name,
            @JsonProperty("poster_path") String posterPath,
            @JsonProperty("backdrop_path") String backdropPath
    ) {}

    public record ProductionCompanyDto(
            Integer id,
            @JsonProperty("logo_path") String logoPath,
            String name,
            @JsonProperty("origin_country") String originCountry
    ) {}
}