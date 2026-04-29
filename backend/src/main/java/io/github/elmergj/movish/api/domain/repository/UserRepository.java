package io.github.elmergj.movish.api.domain.repository;

import io.github.elmergj.movish.api.domain.model.entity.user.AuthId;
import io.github.elmergj.movish.api.domain.model.entity.user.Email;
import io.github.elmergj.movish.api.domain.model.entity.user.User;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;

import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(UserId id);

    Optional<User> findByAuthId(AuthId id);

    boolean existsByEmail(Email email);

    boolean existsByUsername(String username);

    boolean existsByAuthId(AuthId authId);
}
