package io.github.elmergj.movish.api.application.user;

import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProvisioningService {

    private final UserRepository userRepository;

        public void provisionUser(UserId id) {

    }
}
