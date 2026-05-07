package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.model.entity.library.UserTitle;
import io.github.elmergj.movish.api.domain.model.entity.library.UserTitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.UserTitleRepository;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers.UserTitleJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserTitleRepositoryJpaAdapter implements UserTitleRepository {

    private final JpaUserTitleRepository jpaUserTitleRepository;
    private final UserTitleJpaMapper userTitleJpaMapper;

    @Override
    public void save(UserTitle userTitle) {
        jpaUserTitleRepository.save(userTitleJpaMapper.toJpaUserTitle(userTitle));
    }

    @Override
    public Optional<UserTitle> findById(UserTitleId id) {
        return jpaUserTitleRepository.findById(id.value())
                .map(userTitleJpaMapper::toDomain);
    }

    @Override
    public Optional<UserTitle> findByIdAndUserOwnerId(UserTitleId id, UserId userId) {
        return jpaUserTitleRepository.findByIdAndUserEntity_Id(id.value(), userId.value())
                .map(userTitleJpaMapper::toDomain);
    }

    @Override
    public void delete(UserTitle userTitle) {
        jpaUserTitleRepository.delete(userTitleJpaMapper.toJpaUserTitle(userTitle));
    }
}
