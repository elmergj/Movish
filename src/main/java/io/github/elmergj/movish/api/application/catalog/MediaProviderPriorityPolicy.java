package io.github.elmergj.movish.api.application.catalog;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaExternalId;

import java.util.Collection;

public interface MediaProviderPriorityPolicy {

    MediaExternalId select(Collection<MediaExternalId> externalIds);
}
