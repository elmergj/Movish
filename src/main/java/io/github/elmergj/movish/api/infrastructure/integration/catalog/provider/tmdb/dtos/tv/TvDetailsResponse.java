package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * DTO for TV series details.
 * Note: Nested records are used to encapsulate structures that are 100%
 * dependent on the context of this endpoint, avoiding file pollution in the package.
 */
public record TvDetailsResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("name") String name,
        @JsonProperty("original_name") String originalName,
        @JsonProperty("overview") String overview,
        @JsonProperty("tagline") String tagline,
        @JsonProperty("status") String status,
        @JsonProperty("type") String type,
        @JsonProperty("popularity") Double popularity,
        @JsonProperty("vote_average") Double voteAverage,
        @JsonProperty("vote_count") Integer voteCount,
        @JsonProperty("first_air_date") String firstAirDate,
        @JsonProperty("last_air_date") String lastAirDate,
        @JsonProperty("backdrop_path") String backdropPath,
        @JsonProperty("poster_path") String posterPath,
        @JsonProperty("homepage") String homepage,
        @JsonProperty("in_production") boolean inProduction,
        @JsonProperty("number_of_episodes") Integer numberOfEpisodes,
        @JsonProperty("number_of_seasons") Integer numberOfSeasons,
        @JsonProperty("episode_run_time") List<Integer> episodeRunTime,
        @JsonProperty("languages") List<String> languages,
        @JsonProperty("origin_country") List<String> originCountry,
        @JsonProperty("original_language") String originalLanguage,
        @JsonProperty("genres") List<Genre> genres,
        @JsonProperty("created_by") List<Creator> createdBy,
        @JsonProperty("networks") List<Network> networks,
        @JsonProperty("production_companies") List<Company> productionCompanies,
        @JsonProperty("production_countries") List<Country> productionCountries,
        @JsonProperty("seasons") List<Season> seasons,
        // REUSE: The episode record is used for both fields since they share
        // the same chapter metadata structure, allowing nullability.
        @JsonProperty("last_episode_to_air") Episode lastEpisodeToAir,
        @JsonProperty("next_episode_to_air") Episode nextEpisodeToAir,
        @JsonProperty("spoken_languages") List<SpokenLanguage> spokenLanguages
) {
    public record Genre(
            @JsonProperty("id") Integer id,
            @JsonProperty("name") String name
    ) {}

    public record Creator(
            @JsonProperty("id") Integer id,
            @JsonProperty("credit_id") String creditId,
            @JsonProperty("name") String name,
            @JsonProperty("gender") Integer gender,
            @JsonProperty("profile_path") String profilePath
    ) {}

    /* CONSIDERATION: Network and Company are defined separately even though they are similar
     because in the TMDB API they represent different semantic concepts, and they may evolve
     with different fields (e.g., Company often includes origin_country).*/

    public record Network(
            @JsonProperty("id") Integer id,
            @JsonProperty("name") String name,
            @JsonProperty("logo_path") String logoPath,
            @JsonProperty("origin_country") String originCountry
    ) {}

    public record Company(
            @JsonProperty("id") Integer id,
            @JsonProperty("name") String name,
            @JsonProperty("logo_path") String logoPath,
            @JsonProperty("origin_country") String originCountry
    ) {}

    public record Country(
            @JsonProperty("iso_3166_1") String iso31661,
            @JsonProperty("name") String name
    ) {}

    public record SpokenLanguage(
            @JsonProperty("english_name") String englishName,
            @JsonProperty("iso_639_1") String iso6391,
            @JsonProperty("name") String name
    ) {}

    public record Season(
            @JsonProperty("id") Long id,
            @JsonProperty("air_date") String airDate,
            @JsonProperty("episode_count") Integer episodeCount,
            @JsonProperty("name") String name,
            @JsonProperty("overview") String overview,
            @JsonProperty("poster_path") String posterPath,
            @JsonProperty("season_number") Integer seasonNumber,
            @JsonProperty("vote_average") Double voteAverage
    ) {}

    public record Episode(
            @JsonProperty("id") Long id,
            @JsonProperty("name") String name,
            @JsonProperty("overview") String overview,
            @JsonProperty("air_date") String airDate,
            @JsonProperty("episode_number") Integer episodeNumber,
            @JsonProperty("season_number") Integer seasonNumber,
            @JsonProperty("vote_average") Double voteAverage,
            @JsonProperty("vote_count") Integer voteCount,
            @JsonProperty("still_path") String stillPath,
            @JsonProperty("runtime") Integer runtime,
            @JsonProperty("production_code") String productionCode,
            @JsonProperty("show_id") Long showId
    ) {}
}