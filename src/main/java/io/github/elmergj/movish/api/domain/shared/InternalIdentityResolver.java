package io.github.elmergj.movish.api.domain.shared;

import io.github.elmergj.movish.api.domain.model.entity.user.UserId;

public interface InternalIdentityResolver {
    UserId resolveInternalId(String AuthId);
}

