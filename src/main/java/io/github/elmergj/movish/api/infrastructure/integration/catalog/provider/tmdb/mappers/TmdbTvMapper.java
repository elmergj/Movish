package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers;

import io.github.elmergj.movish.api.application.catalog.search.MediaBasicResult;
import io.github.elmergj.movish.api.application.catalog.search.MediaDetailsResult;
import io.github.elmergj.movish.api.domain.model.entity.library.MediaType;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv.TvDetailsResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TmdbTvMapper {

    public MediaDetailsResult toMediaDetailResult(TvDetailsResponse response) {
        return new MediaDetailsResult(
                response.id().toString(),
                response.originalName(),
                LocalDate.parse(response.firstAirDate()),
                MediaType.TV,
                response.voteAverage());
    }

    public MediaBasicResult toMediaBasicResult(TvDetailsResponse response) {
        return new MediaBasicResult(
                response.id().toString(),
                "tv",
                response.originalName()
        );
    }
}
