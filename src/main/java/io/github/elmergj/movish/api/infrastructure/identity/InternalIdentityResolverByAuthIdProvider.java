package io.github.elmergj.movish.api.infrastructure.identity;

import io.github.elmergj.movish.api.domain.exception.EntityNotFoundException;
import io.github.elmergj.movish.api.domain.model.entity.user.AuthId;
import io.github.elmergj.movish.api.domain.model.entity.user.User;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.UserRepository;
import io.github.elmergj.movish.api.domain.shared.InternalIdentityResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InternalIdentityResolverByAuthIdProvider implements InternalIdentityResolver {

    private final UserRepository userRepository;

    @Cacheable(value = "user_mappings", key = "#authId")
    @Override
    public UserId resolveInternalId(String authId) {

        return userRepository.findByAuthId(AuthId.of(authId))
                .map(User::id)
                .orElseThrow(() -> new EntityNotFoundException(authId));
    }
}
