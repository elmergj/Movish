package io.github.elmergj.movish.api.infrastructure.integration.authentication;

import io.github.elmergj.movish.api.domain.model.entity.user.AuthId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserAuthenticator;
import org.springframework.stereotype.Component;

@Component
public class FirebaseUserAuthenticator implements UserAuthenticator {

    //Task: Complete implementation
    @Override
    public AuthId authenticate(String token) {
        return new AuthId(token + "_TOKEN_VALIDATED");
    }
}
