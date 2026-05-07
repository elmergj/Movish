package io.github.elmergj.movish.api.interfaces.rest.user;

import io.github.elmergj.movish.api.application.user.UpdateUserProfileCommand;
import io.github.elmergj.movish.api.application.user.UserAccountService;
import io.github.elmergj.movish.api.application.user.UserDetailsView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getUserProfileDetails(@AuthenticationPrincipal String userId) {
        UserDetailsView view = userAccountService.getUserDetails(userId);
        var response = new UserProfileResponse(view.email(), view.name(), view.username(),  view.userAvatarId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileResponse> updateUserProfile(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody UpdateUserProfileRequest request) {

        var command = new UpdateUserProfileCommand(
                request.username(),
                request.name(),
                request.email());

        UserDetailsView view = userAccountService.updateUserProfile(userId, command);

        var response = new UserProfileResponse(view.email(), view.name(), view.username(),  view.userAvatarId());

        return ResponseEntity.ok().body(response);
    }
}