package io.github.elmergj.movish.api.interfaces.rest.user;

import io.github.elmergj.movish.api.application.user.UserRegistrationService;
import io.github.elmergj.movish.api.application.user.RegisterUserCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    @PostMapping
    public ResponseEntity<UserRegisteredResponse> registerUser(
            @AuthenticationPrincipal String authId, @Valid @RequestBody RegisterUserRequest request) {

        var command = new RegisterUserCommand(authId, request.email(), request.name());
        var userRegisteredView = userRegistrationService.registerUser(command);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uriResponse}")
                .buildAndExpand(userRegisteredView.id())
                .toUri();

        var response = new UserRegisteredResponse(userRegisteredView.email(), userRegisteredView.name());

        //Test: testing only
        return ResponseEntity.created(location).body(response);
    }
}