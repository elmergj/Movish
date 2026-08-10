package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers;

import io.github.elmergj.movish.api.application.catalog.MediaDetails;
import io.github.elmergj.movish.api.application.catalog.MediaOverview;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie.MovieDetailsResponse;
import org.springframework.stereotype.Component;

@Component
public final class TmdbMovieMapper{

    public MediaDetails toMediaDetails(MovieDetailsResponse response) {
        return new MediaDetails(
                response.id().toString(),
                response.originalTitle(),
                response.releaseDate(),
                "movie",
                response.voteAverage());
    }

    public MediaOverview toMediaOverview(MovieDetailsResponse response) {
        return new MediaOverview(
                response.id().toString(),
                "movie",
                response.originalTitle()
        );
    }
}
