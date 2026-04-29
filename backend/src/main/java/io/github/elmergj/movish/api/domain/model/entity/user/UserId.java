package io.github.elmergj.movish.api.domain.model.entity.user;

import io.github.elmergj.movish.api.domain.shared.BaseId;

public final class UserId extends BaseId {

    private UserId(String id) {
        super(id);
    }

    public static UserId from(String id) {
        return new UserId(id);
    }
}
