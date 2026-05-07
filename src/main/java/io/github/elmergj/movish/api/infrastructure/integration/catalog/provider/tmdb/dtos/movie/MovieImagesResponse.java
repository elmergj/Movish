package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MovieImagesResponse(
        Long id,
        List<ImageDto> backdrops,
        List<ImageDto> logos,
        List<ImageDto> posters
) {
    public record ImageDto(
            @JsonProperty("aspect_ratio")
            Double aspectRatio,

            @JsonProperty("file_path")
            String filePath,

            Integer height,
            Integer width,

            @JsonProperty("iso_639_1")
            String languageCode,

            @JsonProperty("vote_average")
            Double voteAverage,

            @JsonProperty("vote_count")
            Integer voteCount
    ) {}
}