package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.application.library.query.UserTitleQueries;
import io.github.elmergj.movish.api.application.library.query.UserTitleSummaryQueryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserTitleQueryRepositoryAdapter implements UserTitleQueries {

    private final JpaUserTitleReadOnlyRepository repository;

    @Override
    public Optional<UserTitleSummaryQueryResult> getUserTitleSummary(String id) {
        return Optional.empty();
    }

    @Override
    public List<UserTitleSummaryQueryResult> getUserTitleSummaryMatching(Collection<String> ids) {
        return repository.findAllByIdIn(ids.stream().toList());
    }
}
