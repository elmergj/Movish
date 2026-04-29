package io.github.elmergj.movish.api.domain.model.entity.user;

import io.github.elmergj.movish.api.domain.shared.BaseEntity;
import io.github.elmergj.movish.api.domain.shared.Ensure;

public class User extends BaseEntity<User, UserId> {

    private final AuthId authId;
    private Email email;
    private String name;
    private String username;
    private ProfileImage profileImage;

    //Constructors
    private User(UserId id, AuthId authId, String name, Email email) {
        super(id);
        this.authId = authId;
        this.name = name;
        this.email = email;
    }

    private User(UserId id, AuthId authId, String name, Email email, String username, ProfileImage profileImage) {
        super(id);
        this.authId = authId;
        this.email = email;
        this.name = name;
        this.username = username;
        this.profileImage = profileImage;
    }

    // Gettters/Setters
    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public AuthId getAuthId() {
        return authId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

//    public static User create(authId id, AuthId authId, String name, Email email) {
//        Ensure.that(name, "Name").isNotBlank().maxLength(32).minLength(2);
//        return new User(id, authId, name, email);
//    }

    //Methods
    public static User create(UserId id, AuthId authId, String name, Email email, String username, ProfileImage profileImage) {
        Ensure.that(name, "Name").isNotBlank().minLength(2).maxLength(16);
        Ensure.that(username, "Username").isNotBlank().minLength(6).maxLength(16);
        Ensure.that(profileImage, "Profile image").isNotBlank();

        return new User(id, authId, name, email, username, profileImage);
    }

    public static User fromExisting(UserId id, AuthId authId, String name, Email email, String username, ProfileImage profileImage){
        return new User(id, authId, name, email, username, profileImage);
    }
}
