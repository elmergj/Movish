package io.github.elmergj.movish.api.interfaces.rest.catalog;

import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchResultSet;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.MediaSummaryResult;

public record TitleSearchResponse(
        //Optimize: Map to a better http response body, is the page and total itemsDetails part of the response or the search results?
//        int page,
//        List<MediaSummaryResult> itemsDetails,
//        long total_results,
//        long total_pages
        SearchResultSet<MediaSummaryResult> results
) {

}
