package io.github.elmergj.movish.api.domain.repository;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaExternalId;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaId;
import io.github.elmergj.movish.api.domain.model.entity.library.Title;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;

import java.util.Collection;
import java.util.Optional;

public interface TitleRepository {

    void save(Title title);

    Optional<Title> findById(TitleId id);

    Optional<Title> findByIdAndUserId(TitleId id, UserId userId);

    Optional<Title> findByMediaIdAndUserId(MediaId mediaId, UserId userId);

    boolean existsByMediaIdAndUserId(MediaId id, UserId userId);

    boolean existsByMediaExternalIds(UserId userId, Collection<MediaExternalId> externalIds);

    void delete(Title title);
}
