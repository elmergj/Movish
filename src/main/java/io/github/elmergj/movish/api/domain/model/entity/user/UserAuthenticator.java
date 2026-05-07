package io.github.elmergj.movish.api.domain.model.entity.user;

public interface UserAuthenticator {
    AuthId authenticate(String token);
}
