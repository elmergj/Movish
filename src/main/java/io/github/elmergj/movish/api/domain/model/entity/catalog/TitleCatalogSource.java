package io.github.elmergj.movish.api.domain.model.entity.catalog;

import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchFilter;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchResultSet;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.TitleDetailsResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.TitleSummaryResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.MediaType;

public interface TitleCatalogSource {

    SearchResultSet<TitleSummaryResult> searchTitleByQuery(String query, int page, int pageSize);

    SearchResultSet<TitleSummaryResult> searchTitleByFilters(SearchFilter filter, int page, int pageSize);

    TitleDetailsResult fetchTitleDetails(String externalTitleId, MediaType mediaType);
}
