package io.github.elmergj.movish.api.application.catalog;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaProvider;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaProviderId;

public interface MediaProviderRegistry {

    MediaProviderId findByExternalName(String externalName);

    MediaProvider findById(MediaProviderId id);

    String findExternalNameById(MediaProviderId id);
}
