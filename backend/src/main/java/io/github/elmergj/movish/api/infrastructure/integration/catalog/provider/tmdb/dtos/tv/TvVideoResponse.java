package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * DTO for the response of videos associated with a TV series.
 * Provides metadata about trailers, clips, and behind-the-scenes material.
 */
public record TvVideoResponse(
        @JsonProperty("id") Long id,
        @JsonProperty("results") List<VideoResult> results
) {
    /**
     * REUSE: An internal record is defined for video details.
     * Note: The 'key' field is essential for building the video URL
     * based on the value of the 'site' field (e.g., YouTube or Vimeo).
     */
    public record VideoResult(
            @JsonProperty("id") String id, // ID interno de TMDB para el video
            @JsonProperty("iso_639_1") String language,
            @JsonProperty("iso_3166_1") String region,
            @JsonProperty("name") String name,
            @JsonProperty("key") String key,
            @JsonProperty("site") String site,
            @JsonProperty("size") Integer size,
            @JsonProperty("type") String type,
            @JsonProperty("official") boolean official,
            @JsonProperty("published_at") String publishedAt
    ) {}
}