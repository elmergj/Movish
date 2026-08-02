package io.github.elmergj.movish.api.domain.model.entity.catalog;

import io.github.elmergj.movish.api.application.catalog.search.MediaBasicResult;
import io.github.elmergj.movish.api.application.catalog.search.MediaDetailsResult;
import io.github.elmergj.movish.api.application.catalog.search.MediaSummaryResult;
import io.github.elmergj.movish.api.application.catalog.search.SearchFilter;
import io.github.elmergj.movish.api.application.catalog.search.SearchResultSet;

public interface MediaCatalogSource {

    SearchResultSet<MediaSummaryResult> searchMediaByQuery(String query, int page, int pageSize);

    SearchResultSet<MediaSummaryResult> searchTitleByFilters(SearchFilter filter, int page, int pageSize);

    MediaDetailsResult fetchMediaDetails(String mediaId, String mediaType);

    MediaSummaryResult fetchMediaSummary(String mediaId, String mediaType);

    MediaBasicResult fetchMediaBasicData(String mediaId, String mediaType);
}
