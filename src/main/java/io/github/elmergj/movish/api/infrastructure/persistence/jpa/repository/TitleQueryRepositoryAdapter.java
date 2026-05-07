package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.application.catalog.query.TitleDetailsQueryResult;
import io.github.elmergj.movish.api.application.catalog.query.TitleQueries;
import io.github.elmergj.movish.api.application.catalog.query.TitleSummaryQueryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TitleQueryRepositoryAdapter implements TitleQueries {

    private final JpaTitleReadOnlyRepository repository;

    @Override
    public Optional<TitleSummaryQueryResult> getTitleSummary(String externalId) {
        return Optional.empty();
    }

    @Override
    public List<TitleSummaryQueryResult> getTitleSummaryMatching(Collection<String> externalIds) {
        return repository.findAllByIdIn(externalIds.stream().toList());
    }

    @Override
    public Optional<TitleDetailsQueryResult> getTitleDetails(String externalId) {
        return Optional.empty();
    }
}
