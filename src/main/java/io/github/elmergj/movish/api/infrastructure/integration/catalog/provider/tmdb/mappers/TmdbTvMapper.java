package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers;

import io.github.elmergj.movish.api.application.catalog.MediaOverview;
import io.github.elmergj.movish.api.application.catalog.MediaDetails;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv.TvDetailsResponse;
import org.springframework.stereotype.Component;

@Component
public class TmdbTvMapper {

    public MediaDetails toMediaDetails(TvDetailsResponse response) {
        return new MediaDetails(
                response.id().toString(),
                response.originalName(),
                response.firstAirDate(),
                "tv",
                response.voteAverage()
                );
    }

    public MediaOverview toMediaOverview(TvDetailsResponse response) {
        return new MediaOverview(
                response.id().toString(),
                "tv",
                response.originalName()
        );
    }
}
