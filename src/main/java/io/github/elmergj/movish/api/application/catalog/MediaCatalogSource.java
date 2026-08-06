package io.github.elmergj.movish.api.application.catalog;

import io.github.elmergj.movish.api.application.catalog.search.MediaSearchResult.MediaSummaryResult;
import io.github.elmergj.movish.api.application.catalog.search.SearchFilter;
import io.github.elmergj.movish.api.application.catalog.search.SearchResultSet;

public interface MediaCatalogSource {

    MediaDetails fetchMediaDetails(String mediaId, String mediaType);

    MediaOverview fetchMediaOverview(String mediaId, String mediaType);

    // Search

    SearchResultSet<MediaSummaryResult> searchMediaByQuery(String query, int page, int pageSize);

    SearchResultSet<MediaSummaryResult> searchTitleByFilters(SearchFilter filter, int page, int pageSize);

    MediaSummaryResult fetchMediaSummary(String mediaId, String mediaType);
}
