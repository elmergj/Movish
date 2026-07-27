package io.github.elmergj.movish.api.application.catalog.query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CatalogMediaQueries {

    Optional<MediaSummaryQueryResult> getMediaSummary(String externalId);

    List<MediaSummaryQueryResult> getMediaSummaryMatching(Collection<String> externalIds);

    Optional<CatalogMediaDetailsQueryResult> getMediaDetails(String externalId);
}
