package io.github.elmergj.movish.api.domain.shared;

import io.github.elmergj.movish.api.domain.model.entity.user.UserId;

public interface AssetOwnerValidator {
    void validateAssetOwner(UserId userId, UserAsset asset);
}
