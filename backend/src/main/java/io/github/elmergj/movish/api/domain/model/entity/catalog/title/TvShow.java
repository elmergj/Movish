package io.github.elmergj.movish.api.domain.model.entity.catalog.title;

import java.util.List;

public record TvShow(
        List<TvEpisode> episodes,
        List<TvSeason> seasons)
        implements MediaTypeDetails{
}
