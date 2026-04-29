package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.model.entity.catalog.title.MediaType;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.Title;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.TitleId;
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
    public Optional<Title> findById(TitleId titleId){
        return jpaTitleRepository.findById(titleId.value())
                .map(titleJpaMapper::toDomain);
    }

    @Override
    public Optional<Title> findByExternalIdAndMediaType(String externalId, MediaType mediaType) {
        return jpaTitleRepository.findByIdAndMediaType(externalId, mediaType)
                .map(titleJpaMapper::toDomain);
    }
}
