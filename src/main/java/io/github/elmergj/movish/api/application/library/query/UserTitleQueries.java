package io.github.elmergj.movish.api.application.library.query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserTitleQueries {

    Optional<TitleSummaryQueryResult> getUserTitleSummary(String id);

    List<TitleSummaryQueryResult> getUserTitleSummaryMatching(Collection<String> ids);
}
