package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb;

import io.github.elmergj.movish.api.application.catalog.MediaCatalogSource;
import io.github.elmergj.movish.api.application.catalog.MediaDetails;
import io.github.elmergj.movish.api.application.catalog.MediaOverview;
import io.github.elmergj.movish.api.application.catalog.search.MediaSearchResult.MediaSummaryResult;
import io.github.elmergj.movish.api.application.catalog.search.SearchFilter;
import io.github.elmergj.movish.api.application.catalog.search.SearchResultSet;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie.MovieDetailsResponse;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv.TvDetailsResponse;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers.TmdbMovieMapper;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers.TmdbTvMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Profile({"prod"})
public class TmdbCatalogSource implements MediaCatalogSource {

//    private static final Logger log = LoggerFactory.getLogger(TmdbCatalogSource.class);

    private final TmdbMovieMapper movieMapper;
    private final TmdbTvMapper tvMapper;
    private final RestClient restClient;

    @Override
    public MediaOverview fetchMediaOverview(String mediaId, String mediaType) {
        return switch (mediaType) {
            case "movie" -> {
                var clientResponse = restClient.get()
                        .uri("/movie/{id}", mediaId)
                        .retrieve()
                        .body(MovieDetailsResponse.class);
                yield movieMapper.toMediaOverview(Objects.requireNonNull(clientResponse));
            }
            case "tv" -> {
                var clientResponse = restClient.get()
                        .uri("/tv/{id}", mediaId)
                        .retrieve()
                        .body(TvDetailsResponse.class);
                yield tvMapper.toMediaOverview(Objects.requireNonNull(clientResponse));
            }
            default -> throw new IllegalStateException("Unexpected value: " + mediaType);
        };
    }

    @Override
    public MediaDetails fetchMediaDetails(String mediaId, String mediaType) {
        return switch (mediaType) {
            case "movie" -> {
                var clientResponse = restClient.get()
                        .uri("/movie/{id}", mediaId)
                        .retrieve()
                        .body(MovieDetailsResponse.class);
                yield movieMapper.toMediaDetails(Objects.requireNonNull(clientResponse));
            }
            case "tv" -> {
                var clientResponse = restClient.get()
                        .uri("/tv/{id}", mediaId)
                        .retrieve()
                        .body(TvDetailsResponse.class);
                yield tvMapper.toMediaDetails(Objects.requireNonNull(clientResponse));
            }
            default -> throw new IllegalStateException("Unexpected value: " + mediaType);
        };
    }

    // Search
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
    public MediaSummaryResult fetchMediaSummary(String mediaId, String mediaType) {
        return null;
    }
}