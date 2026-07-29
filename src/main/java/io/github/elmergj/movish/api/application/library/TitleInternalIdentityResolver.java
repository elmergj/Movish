package io.github.elmergj.movish.api.application.library;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaExternalId;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaId;

import java.util.Collection;

public interface TitleInternalIdentityResolver {

    MediaId resolveByExternalIds(Collection<MediaExternalId> mediaExternalIds);
}
