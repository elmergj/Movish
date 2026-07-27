package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaType;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.MediaSummaryResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchResultSet;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Profile("dev") //Test: test only
public class FakeTitleSearchResult {

    // Test: Using a Fake Test Double
    public static SearchResultSet<MediaSummaryResult> getFakeTitleSearchResult(){

        List<MediaSummaryResult> results = new ArrayList<>();
        MediaSummaryResult fakeMediaSummaryResult = new MediaSummaryResult(
                "597",
                "Titanic",
                "/image.jpg",
                null, //Bug: to solve!
                MediaType.MOVIE.name());
        results.add(fakeMediaSummaryResult);

        return new SearchResultSet<>(results, 1, 12, false);
    }
}
