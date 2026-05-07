package io.github.elmergj.movish.api.domain.model.entity.user;

public interface ProfileImagePolicy {

    ProfileImage assignDefaultProfileImage();

    ProfileImage assignUserUploadedProfileImage(String uploadedImageId);

}
