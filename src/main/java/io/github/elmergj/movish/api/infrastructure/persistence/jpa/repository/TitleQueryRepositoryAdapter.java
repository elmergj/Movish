package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.application.library.query.UserTitleQueries;
import io.github.elmergj.movish.api.application.library.query.TitleSummaryQueryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TitleQueryRepositoryAdapter implements UserTitleQueries {

    private final JpaTitleReadOnlyRepository repository;

    @Override
    public Optional<TitleSummaryQueryResult> getUserTitleSummary(String id) {
        return Optional.empty();
    }

    @Override
    public List<TitleSummaryQueryResult> getUserTitleSummaryMatching(Collection<String> ids) {
        return repository.findAllByIdIn(ids.stream().toList());
    }
}
