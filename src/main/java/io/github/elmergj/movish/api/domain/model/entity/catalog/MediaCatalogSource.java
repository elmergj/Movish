package io.github.elmergj.movish.api.domain.model.entity.catalog;

import io.github.elmergj.movish.api.domain.model.entity.catalog.search.MediaSummaryResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchFilter;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchResultSet;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.MediaDetailsResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaType;

public interface MediaCatalogSource {

    SearchResultSet<MediaSummaryResult> searchMediaByQuery(String query, int page, int pageSize);

    SearchResultSet<MediaSummaryResult> searchTitleByFilters(SearchFilter filter, int page, int pageSize);

    MediaDetailsResult fetchMediaDetails(String mediaExternalId, MediaType mediaType);
}
