package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.model.entity.user.AuthId;
import io.github.elmergj.movish.api.domain.model.entity.user.Email;
import io.github.elmergj.movish.api.domain.model.entity.user.User;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.UserRepository;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers.UserJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJpaAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserJpaMapper userJpaMapper;

    @Override
    public void save(User user) {
        jpaUserRepository.save(userJpaMapper.toJpaUser(user));
    }

    //Optimize: findByProviderId(String id) can receive and String null via authId id.value()
    @Override
    public Optional<User> findById(UserId id) {
        return jpaUserRepository.findById(userJpaMapper.toJpaId(id))
                .map(userJpaMapper::toDomain);
    }

    @Override
    public Optional<User> findByAuthId(AuthId authId) {
        return jpaUserRepository.findByAuthId(userJpaMapper.toJpaAuthId(authId))
                .map(userJpaMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaUserRepository.existsByEmail(email.value());
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByAuthId(AuthId authId) {
        return jpaUserRepository.existsByAuthId(authId.value());
    }
}
