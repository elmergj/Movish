package io.github.elmergj.movish.api.domain.repository;

import io.github.elmergj.movish.api.domain.model.entity.catalog.title.MediaType;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.Title;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.TitleId;

import java.util.Optional;

public interface TitleRepository {

    void save(Title title);

    Optional<Title> findById(TitleId titleId);

    Optional<Title> findByExternalIdAndMediaType(String externalId, MediaType mediaType);
}

