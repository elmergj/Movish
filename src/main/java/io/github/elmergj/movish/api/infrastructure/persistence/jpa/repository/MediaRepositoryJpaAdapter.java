package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MediaRepositoryJpaAdapter implements MediaRepository {

//    private final JpaMediaRepository jpaMediaRepository;
//    private final MediaJpaMapper mediaJpaMapper;
//
//    @Override
//    public void save(Media media) {
//        jpaMediaRepository.save(mediaJpaMapper.toJpaMedia(media));
//    }
//
//    @Override
//    public Optional<Media> findById(MediaId mediaId){
//        return jpaMediaRepository.findById(mediaId.value())
//                .map(mediaJpaMapper::toDomain);
//    }
//
//    @Override
//    public Optional<Media> findByExternalIdAndMediaType(String mediaId, MediaType mediaType) {
//        return jpaMediaRepository.findByIdAndMediaType(mediaId, mediaType)
//                .map(mediaJpaMapper::toDomain);
//    }
}
