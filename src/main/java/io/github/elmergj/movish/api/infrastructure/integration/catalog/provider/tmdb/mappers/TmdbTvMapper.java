package io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.mappers;

import io.github.elmergj.movish.api.application.catalog.search.MediaDetailsResult;
import io.github.elmergj.movish.api.domain.model.entity.library.MediaType;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.TitleCatalogMapper;
import io.github.elmergj.movish.api.infrastructure.integration.catalog.provider.tmdb.dtos.tv.TvDetailsResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TmdbTvMapper implements TitleCatalogMapper<TvDetailsResponse> {

    @Override
    public MediaDetailsResult toCatalogResult(TvDetailsResponse response) {
        return new MediaDetailsResult(
                response.id().toString(),
                response.originalName(),
                LocalDate.parse(response.firstAirDate()),
                MediaType.TV_SHOW,
                response.voteAverage());
    }
}
