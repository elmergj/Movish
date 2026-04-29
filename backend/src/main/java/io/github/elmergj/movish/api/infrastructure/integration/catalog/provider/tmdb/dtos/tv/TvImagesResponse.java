package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TvImagesResponse(
        @JsonProperty("file_path") String filePath,
        @JsonProperty("iso_639_1") String language,
        double voteAverage,
        int width,
        int height
) {}