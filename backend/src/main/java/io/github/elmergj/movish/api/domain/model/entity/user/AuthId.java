package io.github.elmergj.movish.api.domain.model.entity.user;

public record AuthId(String value)  {

    public static AuthId of(String key) {
        return new AuthId(key);
    }
}
