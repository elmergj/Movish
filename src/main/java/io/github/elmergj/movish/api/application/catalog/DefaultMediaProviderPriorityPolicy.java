package io.github.elmergj.movish.api.application.catalog;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaExternalId;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DefaultMediaProviderPriorityPolicy implements MediaProviderPriorityPolicy {

    @Override
    public MediaExternalId select(Collection<MediaExternalId> externalIds) {
        return externalIds.stream()
                .filter(mediaExternalId -> mediaExternalId.mediaProviderId().name().equals("TMDB"))
                .findFirst()
                .orElseThrow();
    }

}