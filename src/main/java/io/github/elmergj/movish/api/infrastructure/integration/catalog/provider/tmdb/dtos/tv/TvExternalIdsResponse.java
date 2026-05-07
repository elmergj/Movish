package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for mapping external IDs of a TV series.
 * This object is vital for "Deep Linking" strategies and synchronization
 * with external providers (IMDB, Wikidata, etc.).
 */
public record TvExternalIdsResponse(
        Long id,

        @JsonProperty("imdb_id")
        String imdbId,

        @JsonProperty("tvdb_id")
        Long tvdbId,

        @JsonProperty("wikidata_id")
        String wikidataId,

        @JsonProperty("facebook_id")
        String facebookId,

        @JsonProperty("instagram_id")
        String instagramId,

        @JsonProperty("twitter_id")
        String twitterId,

        // These fields are legacy (Freebase/TVRage),
        // included for completeness but are usually optional.
        @JsonProperty("freebase_mid")
        String freebaseMid,

        @JsonProperty("freebase_id")
        String freebaseId,

        @JsonProperty("tvrage_id")
        Long tvrageId
) {}