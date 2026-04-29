package io.github.elmergj.movish.api.application.catalog.query;

import java.time.LocalDate;

public interface TitleSummaryQueryResult {
    String getTitleId();
    String getName();
    Double getTmdbRating();
    LocalDate getReleaseDate();
}
