package io.github.elmergj.movish.api.domain.model.entity.user;

import io.github.elmergj.movish.api.domain.exception.ValidationException;
import io.github.elmergj.movish.api.domain.shared.ValueObject;

public final class ProfileImage implements ValueObject {

    private final String profileImageId;

    private ProfileImage(String value) {
        this.profileImageId = value;
    }

    public static ProfileImage of(String profileImageId) {
        if (profileImageId == null || profileImageId.isBlank()) {
            throw new ValidationException("Image id cannot be empty");
        }
        return new ProfileImage(profileImageId);
    }

    public String value() {
        return profileImageId;
    }

}
