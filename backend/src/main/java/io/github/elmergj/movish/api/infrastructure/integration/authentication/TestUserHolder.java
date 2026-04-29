package io.github.elmergj.movish.api.infrastructure.integration.authentication;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev") //Test: LaSingularidad only
public class TestUserHolder {
    // Un UID por defecto para que los tests no fallen si no configuras nada

    private String currentAuthId = "fake-firebase-uid-001";

    public String getCurrentAuthId() {
        return currentAuthId;
    }

    public void setCurrentAuthId(String authId) {
        this.currentAuthId = authId;
    }

    public void reset() {
        this.currentAuthId = "fake-firebase-uid-001";
    }
}