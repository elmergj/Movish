package io.github.elmergj.movish.api.application.catalog.query;

import java.time.LocalDate;

public interface MediaSummaryQueryResult {
    String getMediaId();
    String getName();
    Double getTmdbRating();
    LocalDate getReleaseDate();
}
