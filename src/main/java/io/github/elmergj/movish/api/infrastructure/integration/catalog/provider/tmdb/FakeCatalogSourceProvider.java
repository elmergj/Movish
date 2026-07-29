package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb;

import io.github.elmergj.movish.api.domain.model.entity.catalog.MediaCatalogSource;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.MediaSummaryResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchFilter;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchResultSet;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.MediaDetailsResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaType;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.FakeTitleSearchResult;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"dev", "local", "default"})
public class FakeCatalogSourceProvider implements MediaCatalogSource {

    @Override
    public SearchResultSet<MediaSummaryResult> searchMediaByQuery(String query, int page, int pageSize) {
        return FakeTitleSearchResult.getFakeTitleSearchResult();
    }

    @Override
    public SearchResultSet<MediaSummaryResult> searchTitleByFilters(SearchFilter filter, int page, int pageSize) {
        return FakeTitleSearchResult.getFakeTitleSearchResult();
    }

    @Override
    public MediaDetailsResult fetchMediaDetails(String mediaExternalId, MediaType mediaType) {
        throw new UnsupportedOperationException("Fake details not implemented yet");
    }
}