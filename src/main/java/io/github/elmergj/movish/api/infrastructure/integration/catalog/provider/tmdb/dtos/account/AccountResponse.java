package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("name") String name,
        @JsonProperty("username") String username,
        @JsonProperty("include_adult") boolean includeAdult,
        @JsonProperty("iso_639_1") String language,
        @JsonProperty("iso_3166_1") String region,
        @JsonProperty("avatar") Avatar avatar
) {
    public record Avatar(
            @JsonProperty("gravatar") Gravatar gravatar,
            @JsonProperty("tmdb") TmdbPath tmdb
    ) {
        public record Gravatar(
                @JsonProperty("hash") String hash
        ) {}

        public record TmdbPath(
                @JsonProperty("avatar_path") String path
        ) {}
    }
}
