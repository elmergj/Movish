package io.github.elmergj.movish.api.domain.repository;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.Media;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaType;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaId;

import java.util.Optional;

public interface MediaRepository {

    void save(Media media);

    Optional<Media> findById(MediaId mediaId);

    Optional<Media> findByExternalIdAndMediaType(String externalId, MediaType mediaType);
}

