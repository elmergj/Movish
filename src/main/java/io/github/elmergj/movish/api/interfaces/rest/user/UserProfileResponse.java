package io.github.elmergj.movish.api.interfaces.rest.user;

public record UserProfileResponse(
        String email,
        String name,
        String username,
        String userAvatarId
) {
}
