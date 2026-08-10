package io.github.elmergj.movish.api.application.catalog;

import io.github.elmergj.movish.api.application.catalog.search.MediaSearchResult.MediaSummaryResult;
import io.github.elmergj.movish.api.application.catalog.search.SearchMediaQuery;
import io.github.elmergj.movish.api.application.catalog.search.SearchResultSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaCatalogService {

    private final MediaCatalogSource mediaCatalogSource;

    public MediaOverview getMediaOverview(String mediaId, String mediaType) {
        return mediaCatalogSource.fetchMediaOverview(mediaId, mediaType);
    }

    public MediaDetails getMediaDetails(String mediaId, String mediaType) {
        return mediaCatalogSource.fetchMediaDetails(mediaId, mediaType);
    }

    // Search
    public SearchResultSet<MediaSummaryResult> searchMediaByQuery(SearchMediaQuery command) {
        return mediaCatalogSource.searchMediaByQuery(command.query(), command.page(),
                command.pageSize());
    }

    public MediaSummaryResult getMediaSummary(String mediaId, String mediaType) {
        return mediaCatalogSource.fetchMediaSummary(mediaId, mediaType);
    }
}