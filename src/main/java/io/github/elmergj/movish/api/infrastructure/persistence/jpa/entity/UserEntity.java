package io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "users",  //preventing db engine reserved word "user"
        indexes = {
                @Index(name = "idx_user_auth_id", columnList = "auth_id", unique = true),
                @Index(name = "idx_user_email", columnList = "email", unique = true),
                @Index(name = "idx_user_username", columnList = "username", unique = true)
        }
)
public class UserEntity {

    @Id
    @Column(nullable = false, updatable = false, length = 16)
    private String id;

    @Column(name = "auth_id", unique = true, nullable = false, length = 64)
    private String authId;

    @Column(length = 32)
    private String name;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Column(unique = true, length = 16)
    private String username;

    @Column(length = 64)
    private String profileImageId;
}
