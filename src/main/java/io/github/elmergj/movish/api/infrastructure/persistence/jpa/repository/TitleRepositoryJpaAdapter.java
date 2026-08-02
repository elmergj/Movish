package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.model.entity.library.MediaId;
import io.github.elmergj.movish.api.domain.model.entity.library.Title;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.TitleRepository;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers.TitleJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TitleRepositoryJpaAdapter implements TitleRepository {

    private final JpaTitleRepository jpaTitleRepository;
    private final TitleJpaMapper titleJpaMapper;

    @Override
    public void save(Title title) {
        jpaTitleRepository.save(titleJpaMapper.toJpaTitle(title));
    }

    @Override
    public Optional<Title> findById(TitleId id) {
        return jpaTitleRepository.findById(id.value())
                .map(titleJpaMapper::toDomain);
    }

    @Override
    public Optional<Title> findByIdAndUserId(TitleId id, UserId userId) {
        return jpaTitleRepository.findByIdAndUserEntity_Id(id.value(), userId.value())
                .map(titleJpaMapper::toDomain);
    }

    @Override
    public Optional<Title> findByMediaIdAndUserId(MediaId mediaId, UserId userId) {
        return jpaTitleRepository.findByMediaIdAndUserEntity_Id(mediaId.value(), userId.value())
                .map(titleJpaMapper::toDomain);
    }

    @Override
    public boolean existsByMediaIdAndUserId(MediaId id, UserId userId) {
        return jpaTitleRepository.existsByMediaIdAndUserEntity_Id(id.value(), userId.value());
    }


    @Override
    public void delete(Title title) {
        jpaTitleRepository.delete(titleJpaMapper.toJpaTitle(title));
    }
}
