package io.github.elmergj.movish.api.domain.model.entity.catalog.media;

import io.github.elmergj.movish.api.domain.shared.BaseId;

public class MediaProviderId extends BaseId {

    private MediaProviderId(String id) {
        super(id);
    }

    public static MediaProviderId from(String id) {
        return new MediaProviderId(id);
    }
}
