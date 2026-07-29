package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.application.catalog.query.CatalogMediaDetailsQueryResult;
import io.github.elmergj.movish.api.application.catalog.query.CatalogMediaQueries;
import io.github.elmergj.movish.api.application.catalog.query.MediaSummaryQueryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CatalogMediaQueryRepositoryAdapter implements CatalogMediaQueries {

    private final JpaMediaReadOnlyRepository repository;

    @Override
    public Optional<MediaSummaryQueryResult> getMediaSummary(String externalId) {
        return Optional.empty();
    }

    @Override
    public List<MediaSummaryQueryResult> getMediaSummaryMatching(Collection<String> externalIds) {
        return repository.findAllByIdIn(externalIds.stream().toList());
    }

    @Override
    public Optional<CatalogMediaDetailsQueryResult> getMediaDetails(String externalId) {
        return Optional.empty();
    }
}
