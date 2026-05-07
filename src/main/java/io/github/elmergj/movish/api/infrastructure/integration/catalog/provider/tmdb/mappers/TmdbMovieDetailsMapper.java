package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers;

import io.github.elmergj.movish.api.domain.model.entity.catalog.search.TitleDetailsResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.MediaType;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.TitleCatalogMapper;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.movie.MovieDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TmdbMovieDetailsMapper implements TitleCatalogMapper<MovieDetailsResponse> {

    @Override
    public TitleDetailsResult toCatalogResult(MovieDetailsResponse response) {
        return new TitleDetailsResult(
                response.id().toString(),
                response.originalTitle(),
                LocalDate.parse(response.releaseDate()),
                MediaType.MOVIE,
                response.voteAverage());
    }
}
