package io.github.elmergj.movish.api.domain.model.entity.library;

import io.github.elmergj.movish.api.domain.shared.BaseId;

public final class UserTitleId extends BaseId {

    private UserTitleId(String id) {
        super(id);
    }

    public static UserTitleId from(String id) {
        return new UserTitleId(id);
    }
}
