package io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers;

import io.github.elmergj.movish.api.domain.model.entity.user.AuthId;
import io.github.elmergj.movish.api.domain.model.entity.user.Email;
import io.github.elmergj.movish.api.domain.model.entity.user.ProfileImage;
import io.github.elmergj.movish.api.domain.model.entity.user.User;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.UserEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserJpaMapper {

    //To JPA Mappers
    public @NonNull UserEntity toJpaUser(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(user.id().value());
        userEntity.setAuthId(user.getAuthId().value());
        userEntity.setName( user.getName() );
        userEntity.setEmail(user.getEmail().value());
        userEntity.setUsername( user.getUsername());
        userEntity.setProfileImageId(user.getProfileImage().value());
        return userEntity;
    }

    //Note: Preventing null from vo's value() used ad jpa impl.
    public @NonNull String toJpaId(UserId id) {
        return entityIdToString(id);
    }

    public @NonNull String toJpaAuthId(AuthId authId){
        return authIdToString(authId);
    }

    //To Domain Mappers
    public User toDomain(UserEntity jpaUser) {
        return User.fromExisting(
                UserId.from(jpaUser.getId()),
                AuthId.of(jpaUser.getAuthId()),
                jpaUser.getName(),
                Email.of(jpaUser.getEmail()),
                jpaUser.getUsername(),
                ProfileImage.of(jpaUser.getProfileImageId()));
    }

    //VO Mappers
    private String emailToString(Email email) {
        return email.value();
    }

    private String authIdToString(AuthId authId) {
        return authId.value();
    }

    private String entityIdToString(UserId id){
        return id.value();
    }

}
