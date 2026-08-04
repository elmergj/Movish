package io.github.elmergj.movish.api.domain.repository;

import io.github.elmergj.movish.api.domain.model.entity.library.MediaId;
import io.github.elmergj.movish.api.domain.model.entity.library.MediaType;
import io.github.elmergj.movish.api.domain.model.entity.library.Title;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;

import java.util.Optional;

public interface TitleRepository {

    void save(Title title);

    Optional<Title> findById(TitleId id);

    Optional<Title> findByIdAndUserId(TitleId id, UserId userId);

    Optional<Title> findUniqueTitle( UserId userId, MediaId mediaId, MediaType mediaType);

    boolean existsUniqueTitle(UserId userId, MediaId id, MediaType mediaType);

    void delete(Title title);
}
