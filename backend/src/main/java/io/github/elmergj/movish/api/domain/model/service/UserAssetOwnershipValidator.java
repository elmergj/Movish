package io.github.elmergj.movish.api.domain.model.service;

import io.github.elmergj.movish.api.domain.exception.DomainRuleViolationException;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.shared.AssetOwnerValidator;
import io.github.elmergj.movish.api.domain.shared.UserAsset;
import org.springframework.stereotype.Component;

@Component
public class UserAssetOwnershipValidator implements AssetOwnerValidator {

    @Override
    public void validateAssetOwner(UserId userId, UserAsset asset){
        if (!asset.getUserOwnerId().equals(userId)){
            throw new DomainRuleViolationException("Unable to verify "+ asset.getAssetName() +" owner");
        }
    }

}
