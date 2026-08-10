package io.github.elmergj.movish.api.domain.model.entity.library;

import io.github.elmergj.movish.api.domain.shared.BaseId;

public final class MediaId extends BaseId {

    private MediaId(String id) {
        super(id);
    }

    public static MediaId from(String id) {
        return new MediaId(id);
    }
}
