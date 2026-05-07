package io.github.elmergj.movish.api.application.user;

import io.github.elmergj.movish.api.domain.exception.EntityNotFoundException;
import io.github.elmergj.movish.api.domain.model.entity.user.Email;
import io.github.elmergj.movish.api.domain.model.entity.user.User;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserRepository userRepository;

    @Transactional
    public UserDetailsView updateUserProfile(String id, UpdateUserProfileCommand command) {

        UserId userId = UserId.from(id);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with value " + id + " not found"));

        //Todo: Add account modification validations
        user.setName(command.name());
        user.setUsername(command.username());
        user.setEmail(Email.of(command.email()));

        userRepository.save(user);
        return new UserDetailsView(user.getEmail().value(),
                user.getName(),
                user.getUsername(),
                user.getProfileImage().value());
    }

    public UserDetailsView getUserDetails(String id) {
        UserId userId = UserId.from(id);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with value " + id + " not found"));
        return new UserDetailsView(user.getEmail().value(),
                user.getName(),
                user.getUsername(),
                user.getProfileImage().value());
    }
}
