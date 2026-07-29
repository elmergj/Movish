package io.github.elmergj.movish.api.application.library.query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TitleQueries {

    Optional<TitleSummaryQueryResult> getTitleSummary(String id);

    List<TitleSummaryQueryResult> getTitleSummaryMatching(Collection<String> ids);
}
