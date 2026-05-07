package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.find;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * DTO for the /find/{external_id} endpoint.
 * TECHNICAL CRITERION: Since this endpoint searches across multiple categories
 * (movies, series, people, etc.), lists are defined for each type of result.
 */
public record FindByIdResponse(

        @JsonProperty("movie_results")
        List<FindMovieResultDto> movieResults,

        @JsonProperty("tv_results")
        List<Object> tvResults, // Se puede tipar más adelante si implementas TV

        @JsonProperty("person_results")
        List<Object> personResults,

        @JsonProperty("tv_episode_results")
        List<Object> tvEpisodeResults,

        @JsonProperty("tv_season_results")
        List<Object> tvSeasonResults
) {

    /**
     * DTO for results specific to movies found via external ID.
     * DESIGN CRITERION:
     * 1. Reuses the standard 'movie' structure from TMDB.
     * 2. Includes 'mediaType' to confirm that the result is indeed a movie.
     */
    public record FindMovieResultDto(
            Long id,
            String title,
            @JsonProperty("original_title")
            String originalTitle,
            String overview,
            @JsonProperty("poster_path")
            String posterPath,
            @JsonProperty("backdrop_path")
            String backdropPath,
            @JsonProperty("media_type")
            String mediaType,
            @JsonProperty("release_date")
            String releaseDate,
            @JsonProperty("genre_ids")
            List<Integer> genreIds,
            @JsonProperty("vote_average")
            Double voteAverage,
            @JsonProperty("vote_count")
            Integer voteCount,
            Boolean adult,
            Double popularity,
            @JsonProperty("original_language")
            String originalLanguage,
            Boolean video
    ) {}
}