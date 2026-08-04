package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb;

import io.github.elmergj.movish.api.application.catalog.search.MediaBasicResult;
import io.github.elmergj.movish.api.application.catalog.search.MediaDetailsResult;
import io.github.elmergj.movish.api.application.catalog.search.MediaSummaryResult;
import io.github.elmergj.movish.api.application.catalog.search.SearchFilter;
import io.github.elmergj.movish.api.application.catalog.search.SearchResultSet;
import io.github.elmergj.movish.api.domain.model.entity.catalog.MediaCatalogSource;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.FakeTitleSearchResult;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie.MovieDetailsResponse;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv.TvDetailsResponse;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers.TmdbMovieMapper;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers.TmdbTvMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile({"dev"})
public class FakeCatalogSourceProvider implements MediaCatalogSource {

    public final RestClient restClient;
    public final TmdbMovieMapper movieMapper;
    public final TmdbTvMapper tvMapper;

    @Override
    public SearchResultSet<MediaSummaryResult> searchMediaByQuery(String query, int page, int pageSize) {
        return FakeTitleSearchResult.getFakeTitleSearchResult();
    }

    @Override
    public SearchResultSet<MediaSummaryResult> searchTitleByFilters(SearchFilter filter, int page, int pageSize) {
        return FakeTitleSearchResult.getFakeTitleSearchResult();
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

        String movieIdTest = "597"; //Titanic
        String tvIdTest = "37680"; //Suits

        if (!mediaId.equals(movieIdTest) && !mediaId.equals(tvIdTest)) {
            log.info("You are in test mode: mediaId received is {}. Valid mediaId are movie {} or tv show {}",
                    mediaId,
                    movieIdTest,
                    tvIdTest);
            return null;
        }

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