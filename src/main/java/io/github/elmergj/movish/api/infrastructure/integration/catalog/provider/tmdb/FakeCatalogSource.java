package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb;

import io.github.elmergj.movish.api.application.catalog.MediaCatalogSource;
import io.github.elmergj.movish.api.application.catalog.MediaDetails;
import io.github.elmergj.movish.api.application.catalog.MediaOverview;
import io.github.elmergj.movish.api.application.catalog.search.MediaSearchResult.MediaSummaryResult;
import io.github.elmergj.movish.api.application.catalog.search.SearchFilter;
import io.github.elmergj.movish.api.application.catalog.search.SearchResultSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile({"dev", "test", "default"})
public class FakeCatalogSource implements MediaCatalogSource {

    @Override
    public MediaDetails fetchMediaDetails(String mediaId, String mediaType) {

        verifyOnlyTestValues(mediaId);

        return switch (mediaType) {
            case "movie" ->  new MediaDetails(
                    "597",
                    "Titanic",
                    "2000-01-01",
                    "movie",
                    8.0
            );
            case "tv" -> new MediaDetails(
                    "37680",
                    "Suits",
                    "2000-01-01",
                    "tv",
                    8.0
                    );
            default -> throw new IllegalStateException("Unexpected value: " + mediaType);
        };
    }

    @Override
    public MediaOverview fetchMediaOverview(String mediaId, String mediaType) {

        verifyOnlyTestValues(mediaId);

        return switch (mediaType) {
            case "movie" ->  new MediaOverview(
                    "597",
                    "movie",
                    "Titanic"
            );
            case "tv" -> new MediaOverview(
                    "37680",
                    "tv",
                    "Suits"
            );
            default -> throw new IllegalStateException("Unexpected value: " + mediaType);
        };
    }

    private void verifyOnlyTestValues(String mediaId) {
        String movieIdTest = "597"; //Titanic
        String tvIdTest = "37680"; //Suits

        if (!mediaId.equals(movieIdTest) && !mediaId.equals(tvIdTest)) {
            log.info("You are in test mode: titleId received is {}. Valid titleId are movie {} or tv show {}",
                    mediaId,
                    movieIdTest,
                    tvIdTest);
            throw new IllegalArgumentException("You are in test mode");
        }
    }

    // Search
    @Override
    public SearchResultSet<MediaSummaryResult> searchMediaByQuery(String query, int page, int pageSize) {
        Collection<MediaSummaryResult> results = new ArrayList<>();
        MediaSummaryResult fakeMediaSummaryResult = new MediaSummaryResult(
                "Titanic",
                "/image.jpg",
                8.90,
                "movie",
                "2000-12-12");
        results.add(fakeMediaSummaryResult);

        return new SearchResultSet<>(results, 1, 12, false);
    }

    @Override
    public SearchResultSet<MediaSummaryResult> searchTitleByFilters(SearchFilter filter, int page, int pageSize) {
        List<MediaSummaryResult> results = new ArrayList<>();
        MediaSummaryResult fakeMediaSummaryResult = new MediaSummaryResult(
                "Titanic",
                "/image.jpg",
                8.90,
                "movie",
                "2000-12-12");
        results.add(fakeMediaSummaryResult);

        return new SearchResultSet<>(results, 1, 12, false);
    }

    @Override
    public MediaSummaryResult fetchMediaSummary(String mediaId, String mediaType) {
        return null;
    }
}