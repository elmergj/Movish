package io.github.elmergj.movish.api.domain.model.entity.catalog.media.tv;

import java.util.List;

public record TvShow(
        String id,
        List<TvEpisode> episodes,
        List<TvSeason> seasons) {
}
