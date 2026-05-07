package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb;

import io.github.elmergj.movish.api.domain.model.entity.catalog.TitleCatalogSource;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchFilter;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchResultSet;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.TitleDetailsResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.TitleSummaryResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.MediaType;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.FakeTitleSearchResult;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie.MovieDetailsResponse;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv.TvDetailsResponse;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers.TmdbMovieDetailsMapper;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers.TmdbTvMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TmdbCatalogSourceProvider implements TitleCatalogSource {

    //Test: test connection
    private static final Logger log = LoggerFactory.getLogger(TmdbCatalogSourceProvider.class);

    private final TmdbMovieDetailsMapper movieMapper;
    private final TmdbTvMapper tvMapper;
    private final RestClient restClient;

    //Task: Complete implementation
    @Override
    public SearchResultSet<TitleSummaryResult> searchTitleByQuery(String query, int page, int pageSize) {

        return FakeTitleSearchResult.getFakeTitleSearchResult();
    }

    //Task: Complete implementation
    @Override
    public SearchResultSet<TitleSummaryResult> searchTitleByFilters(SearchFilter filter, int page, int pageSize) {

        return FakeTitleSearchResult.getFakeTitleSearchResult();
    }

    //Task: Complete implementation
    @Override
    public TitleDetailsResult fetchTitleDetails(String externalTitleId, MediaType mediaType) {
        return switch (mediaType){
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
