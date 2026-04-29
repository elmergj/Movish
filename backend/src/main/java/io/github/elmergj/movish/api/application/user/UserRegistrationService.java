package io.github.elmergj.movish.api.application.user;

import io.github.elmergj.movish.api.domain.exception.ValidationException;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleList;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleListId;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleListType;
import io.github.elmergj.movish.api.domain.model.entity.user.AuthId;
import io.github.elmergj.movish.api.domain.model.entity.user.Email;
import io.github.elmergj.movish.api.domain.model.entity.user.ProfileImagePolicy;
import io.github.elmergj.movish.api.domain.model.entity.user.User;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.TitleListRepository;
import io.github.elmergj.movish.api.domain.repository.UserRepository;
import io.github.elmergj.movish.api.domain.shared.EntityIdGenerator;
import io.github.elmergj.movish.api.infrastructure.integration.authentication.TestUserHolder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final TitleListRepository titleListRepository;

    private final ProfileImagePolicy profileImagePolicy;
    private final EntityIdGenerator entityIdGenerator;
    private final UserProvisioningService userProvisioningService;

    private final TestUserHolder testUserHolder; //Test: test only

    @Transactional
    public UserRegisteredView registerUser(RegisterUserCommand command) {

        String username = "user" + new Random().nextInt(100000, 1000000); //Test: generating a random username

        userExistsValidation(Email.of(command.email()), AuthId.of(command.authId()), username);

        User user = User.create(
                entityIdGenerator.generate(UserId::from),
                AuthId.of(command.authId()),
                command.name(),
                Email.of(command.email()),
                username,
                profileImagePolicy.assignDefaultProfileImage());


        // userProvisioningService.provisionUser(user.id());
        TitleList favoriteList = TitleList.create( // Test: creating the default favorite list
                entityIdGenerator.generate(TitleListId::from),
                user.id(),
                "Favorites",
                TitleListType.FAVORITE_LIST
        );
        //end
        userRepository.save(user);

        titleListRepository.save(favoriteList); // Test: saving the default list

        return new UserRegisteredView(user.getEmail().value(),
                user.getName(),
                user.getUsername(),
                user.id().value());
    }

    // Private Methods
        private void userExistsValidation(Email email, AuthId authId, String username){
        if (userRepository.existsByEmail(email)){
            throw new ValidationException("User with email " + email.value() + " already exists");
        }
        if (userRepository.existsByAuthId(authId)){
            throw new ValidationException("User with uid " + authId.value() + " already exists");
        }
        if (userRepository.existsByUsername(username)){
            throw new ValidationException("User with username " + username + " already exists");
        }
    }
}
