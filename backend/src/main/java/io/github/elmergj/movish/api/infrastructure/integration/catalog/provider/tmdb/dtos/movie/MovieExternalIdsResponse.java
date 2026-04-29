package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for the external identifiers of a movie.
 * Allows linking with social platforms and global databases.
 */
public record MovieExternalIdsResponse(
        Long id,

        @JsonProperty("imdb_id")
        String imdbId,

        @JsonProperty("facebook_id")
        String facebookId,

        @JsonProperty("instagram_id")
        String instagramId,

        @JsonProperty("twitter_id")
        String twitterId,

        @JsonProperty("wikidata_id")
        String wikidataId
) {}