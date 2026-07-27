package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb;

import io.github.elmergj.movish.api.domain.model.entity.catalog.MediaCatalogSource;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.MediaSummaryResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchFilter;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchResultSet;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.MediaDetailsResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaType;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie.MovieDetailsResponse;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv.TvDetailsResponse;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers.TmdbMovieDetailsMapper;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers.TmdbTvMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Component
@Profile("prod")
@RequiredArgsConstructor
public class TmdbCatalogSourceProvider implements MediaCatalogSource {

    private static final Logger log = LoggerFactory.getLogger(TmdbCatalogSourceProvider.class);

    private final TmdbMovieDetailsMapper movieMapper;
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
    public MediaDetailsResult fetchMediaDetails(String externalTitleId, MediaType mediaType) {
        return switch (mediaType) {
            case MOVIE -> {
                var clientResponse = restClient.get()
                        .uri("/movie/{id}", externalTitleId)
                        .retrieve()
                        .body(MovieDetailsResponse.class);

                yield movieMapper.toCatalogResult(Objects.requireNonNull(clientResponse));
            }
            case TV_SHOW -> {
                var clientResponse = restClient.get()
                        .uri("/tv/{id}", externalTitleId)
                        .retrieve()
                        .body(TvDetailsResponse.class);

                yield tvMapper.toCatalogResult(Objects.requireNonNull(clientResponse));
            }
        };
    }
}