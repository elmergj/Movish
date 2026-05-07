package io.github.elmergj.movish.api.domain.model.entity.listing;

import io.github.elmergj.movish.api.domain.shared.BaseId;

public class TitleListId extends BaseId {

    private TitleListId(String id) {
        super(id);
    }

    public static TitleListId from(String id) {
        return new TitleListId(id);
    }
}
