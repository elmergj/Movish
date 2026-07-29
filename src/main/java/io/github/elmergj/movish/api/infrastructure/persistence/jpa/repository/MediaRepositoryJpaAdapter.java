package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.Media;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaId;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaType;
import io.github.elmergj.movish.api.domain.repository.MediaRepository;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers.MediaJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MediaRepositoryJpaAdapter implements MediaRepository {

    private final JpaMediaRepository jpaMediaRepository;
    private final MediaJpaMapper mediaJpaMapper;

    @Override
    public void save(Media media) {
        jpaMediaRepository.save(mediaJpaMapper.toJpaMedia(media));
    }

    @Override
    public Optional<Media> findById(MediaId mediaId){
        return jpaMediaRepository.findById(mediaId.value())
                .map(mediaJpaMapper::toDomain);
    }

    @Override
    public Optional<Media> findByExternalIdAndMediaType(String externalId, MediaType mediaType) {
        return jpaMediaRepository.findByIdAndMediaType(externalId, mediaType)
                .map(mediaJpaMapper::toDomain);
    }
}
