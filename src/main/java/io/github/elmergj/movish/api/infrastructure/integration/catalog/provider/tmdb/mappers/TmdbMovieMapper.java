package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers;

import io.github.elmergj.movish.api.application.catalog.search.MediaBasicResult;
import io.github.elmergj.movish.api.application.catalog.search.MediaDetailsResult;
import io.github.elmergj.movish.api.domain.model.entity.library.MediaType;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie.MovieDetailsResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public final class TmdbMovieMapper{

    public MediaDetailsResult toMediaDetailResult(MovieDetailsResponse response) {
        return new MediaDetailsResult(
                response.id().toString(),
                response.originalTitle(),
                LocalDate.parse(response.releaseDate()),
                MediaType.MOVIE,
                response.voteAverage());
    }

    public MediaBasicResult toMediaBasicResult(MovieDetailsResponse response) {
        return new MediaBasicResult(
                response.id().toString(),
                "movie",
                response.originalTitle()
        );
    }
}
