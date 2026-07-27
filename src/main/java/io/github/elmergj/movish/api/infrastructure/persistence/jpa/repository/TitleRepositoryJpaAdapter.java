package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.model.entity.library.Title;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.UserTitleRepository;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers.UserTitleJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TitleRepositoryJpaAdapter implements UserTitleRepository {

    private final JpaTitleRepository jpaTitleRepository;
    private final UserTitleJpaMapper userTitleJpaMapper;

    @Override
    public void save(Title title) {
        jpaTitleRepository.save(userTitleJpaMapper.toJpaUserTitle(title));
    }

    @Override
    public Optional<Title> findById(TitleId id) {
        return jpaTitleRepository.findById(id.value())
                .map(userTitleJpaMapper::toDomain);
    }

    @Override
    public Optional<Title> findByIdAndUserOwnerId(TitleId id, UserId userId) {
        return jpaTitleRepository.findByIdAndUserEntity_Id(id.value(), userId.value())
                .map(userTitleJpaMapper::toDomain);
    }

    @Override
    public void delete(Title title) {
        jpaTitleRepository.delete(userTitleJpaMapper.toJpaUserTitle(title));
    }
}
