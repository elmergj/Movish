package io.github.elmergj.movish.api.interfaces.rest.admin.media_provider;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequiredArgsConstructor
public class TmdbMediaProviderAuthStatusController {

    private final RestClient restClient;

    @GetMapping("/authentication")
    public ResponseEntity<String> getAuthenticationStatus(){

        var clientResponse = restClient.get()
                .uri("/authentication")
                .retrieve()
                .toEntity(String.class);
        return ResponseEntity.status(clientResponse.getStatusCode())
                .headers(clientResponse.getHeaders())
                .body(clientResponse.getBody());
    }
}
