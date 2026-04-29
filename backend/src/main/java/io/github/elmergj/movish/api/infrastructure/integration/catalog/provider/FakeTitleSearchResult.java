package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider;

import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchResultSet;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.TitleSummaryResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.MediaType;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.TitleRating;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.List;

@Profile("dev") //Test: test only
public class FakeTitleSearchResult {

    // Test: Using a Fake Test Double
    public static SearchResultSet<TitleSummaryResult> getFakeTitleSearchResult(){

        List<TitleSummaryResult> results = new ArrayList<>();
        TitleSummaryResult fakeTitleSummaryResult = new TitleSummaryResult(
                "597",
                "Titanic",
                "/image.jpg",
                TitleRating.of(8.0).value(),
                MediaType.MOVIE.name());
        results.add(fakeTitleSummaryResult);

        return new SearchResultSet<>(results, 1, 12, false);
    }
}
