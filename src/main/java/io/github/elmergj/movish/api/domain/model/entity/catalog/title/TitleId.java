package io.github.elmergj.movish.api.domain.model.entity.catalog.title;

import io.github.elmergj.movish.api.domain.shared.BaseId;

public final class TitleId extends BaseId {

    private TitleId(String id) {
        super(id);
    }

    public static TitleId from(String id) {
        return new TitleId(id);
    }
}
