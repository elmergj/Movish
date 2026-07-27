package io.github.elmergj.movish.api.domain.repository;

import io.github.elmergj.movish.api.domain.model.entity.library.Title;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;

import java.util.Optional;

public interface UserTitleRepository {

    void save(Title title);

    Optional<Title> findById(TitleId id);

    Optional<Title> findByIdAndUserOwnerId(TitleId id, UserId userId);

    void delete(Title title);
}
