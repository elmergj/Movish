package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * DTO Raíz para la respuesta de videos asociados a una película.
 * Mapea el endpoint /movie/{movie_id}/videos.
 */
public record MovieVideosResponse(

        // ID de la película en TMDB al que pertenecen estos videos
        Long id,

        // Lista de recursos multimedia asociados
        List<VideoResultDto> results
) {

    /**
     * Nested DTO for the details of each video resource.
     * DESIGN CRITERION:
     * 1. 'key': This is the most important field, as it contains the video ID
     * in the destination platform (e.g., YouTube ID).
     * 2. 'site': Allows identifying the platform (YouTube, Vimeo) to build
     * the player URL in the frontend.
     * 3. 'type': Essential for filtering and displaying only "Trailer" or "Teaser"
     * according to UI requirements.
     * 4. 'official': Boolean field to prioritize studio-verified content.
     */
    public record VideoResultDto(

            // ID único del video dentro del sistema de TMDB
            String id,

            // Código de idioma (ej. "en", "es")
            @JsonProperty("iso_639_1")
            String iso6391,

            // Código de país (ej. "US", "ES")
            @JsonProperty("iso_3166_1")
            String iso31661,

            // Nombre descriptivo del video
            String name,

            // El identificador del video en la plataforma (ej. YouTube ID)
            String key,

            // La plataforma de origen (habitualmente "YouTube")
            String site,

            // Resolución (ej. 720, 1080)
            Integer size,

            // Categoría: "Trailer", "Teaser", "Clip", "Featurette", "Behind the Scenes"
            String type,

            // Indica si es un recurso oficial del proveedor de contenido
            Boolean official,

            // Fecha de publicación en formato ISO 8601
            @JsonProperty("published_at")
            String publishedAt
    ) {}
}