package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers;

import io.github.elmergj.movish.api.domain.model.entity.catalog.search.MediaDetailsResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaType;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.TitleCatalogMapper;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie.MovieDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TmdbMovieDetailsMapper implements TitleCatalogMapper<MovieDetailsResponse> {

    @Override
    public MediaDetailsResult toCatalogResult(MovieDetailsResponse response) {
        return new MediaDetailsResult(
                response.id().toString(),
                response.originalTitle(),
                LocalDate.parse(response.releaseDate()),
                MediaType.MOVIE,
                response.voteAverage());
    }
}
