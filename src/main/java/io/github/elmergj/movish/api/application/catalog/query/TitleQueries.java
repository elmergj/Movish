package io.github.elmergj.movish.api.application.catalog.query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TitleQueries {

    Optional<TitleSummaryQueryResult> getTitleSummary(String externalId);

    List<TitleSummaryQueryResult> getTitleSummaryMatching(Collection<String> externalIds);

    Optional<TitleDetailsQueryResult> getTitleDetails(String externalId);
}
