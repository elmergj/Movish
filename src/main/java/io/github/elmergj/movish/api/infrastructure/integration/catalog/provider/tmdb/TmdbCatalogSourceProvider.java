package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb;

import io.github.elmergj.movish.api.application.catalog.search.MediaBasicResult;
import io.github.elmergj.movish.api.application.catalog.search.MediaDetailsResult;
import io.github.elmergj.movish.api.application.catalog.search.MediaSummaryResult;
import io.github.elmergj.movish.api.application.catalog.search.SearchFilter;
import io.github.elmergj.movish.api.application.catalog.search.SearchResultSet;
import io.github.elmergj.movish.api.domain.model.entity.catalog.MediaCatalogSource;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie.MovieDetailsResponse;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv.TvDetailsResponse;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers.TmdbMovieMapper;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers.TmdbTvMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Profile("default")
public class TmdbCatalogSourceProvider implements MediaCatalogSource {

    private static final Logger log = LoggerFactory.getLogger(TmdbCatalogSourceProvider.class);

    private final TmdbMovieMapper movieMapper;
    private final TmdbTvMapper tvMapper;
    private final RestClient restClient;

    @Override
    public SearchResultSet<MediaSummaryResult> searchMediaByQuery(String query, int page, int pageSize) {
        // TODO: Implement real TMDB search by query endpoint
        throw new UnsupportedOperationException("Not implemented yet for production");
    }

    @Override
    public SearchResultSet<MediaSummaryResult> searchTitleByFilters(SearchFilter filter, int page, int pageSize) {
        // TODO: Implement real TMDB search by filters endpoint
        throw new UnsupportedOperationException("Not implemented yet for production");
    }

    @Override
    public MediaDetailsResult fetchMediaDetails(String mediaId, String mediaType) {
        return null;
    }

    @Override
    public MediaSummaryResult fetchMediaSummary(String mediaId, String mediaType) {
        return null;
    }

    @Override
    public MediaBasicResult fetchMediaBasicData(String mediaId, String mediaType) {
        return switch (mediaType) {
            case "movie" -> {
                var clientResponse = restClient.get()
                        .uri("/movie/{id}", mediaId)
                        .retrieve()
                        .body(MovieDetailsResponse.class);
                yield movieMapper.toMediaBasicResult(Objects.requireNonNull(clientResponse));
            }
            case "tv" -> {
                var clientResponse = restClient.get()
                        .uri("/tv/{id}", mediaId)
                        .retrieve()
                        .body(TvDetailsResponse.class);
                yield tvMapper.toMediaBasicResult(Objects.requireNonNull(clientResponse));
            }
            default -> throw new IllegalStateException("Unexpected value: " + mediaType);
        };
    }
}