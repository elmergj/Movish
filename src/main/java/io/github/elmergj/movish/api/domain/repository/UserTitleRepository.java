package io.github.elmergj.movish.api.domain.repository;

import io.github.elmergj.movish.api.domain.model.entity.library.UserTitle;
import io.github.elmergj.movish.api.domain.model.entity.library.UserTitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;

import java.util.Optional;

public interface UserTitleRepository {

    void save(UserTitle userTitle);

    Optional<UserTitle> findById(UserTitleId id);

    Optional<UserTitle> findByIdAndUserOwnerId(UserTitleId id, UserId userId);

    void delete(UserTitle userTitle);
}
