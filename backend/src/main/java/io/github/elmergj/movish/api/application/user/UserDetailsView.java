package io.github.elmergj.movish.api.application.user;

public record UserDetailsView(
        String email,
        String name,
        String username,
        String userAvatarId
) {
}
