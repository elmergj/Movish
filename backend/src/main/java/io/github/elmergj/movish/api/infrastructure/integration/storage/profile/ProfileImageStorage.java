package io.github.elmergj.movish.api.infrastructure.integration.storage.profile;

import io.github.elmergj.movish.api.domain.model.entity.user.ProfileImage;
import io.github.elmergj.movish.api.domain.model.entity.user.ProfileImagePolicy;
import org.springframework.stereotype.Component;

@Component
public class ProfileImageStorage implements ProfileImagePolicy {

    @Override
    public ProfileImage assignDefaultProfileImage() {
        return ProfileImage.of("default-avatar.png");
    }

    @Override
    public ProfileImage assignUserUploadedProfileImage(String uploadedImageId) {
        return ProfileImage.of(uploadedImageId);
    }
}
