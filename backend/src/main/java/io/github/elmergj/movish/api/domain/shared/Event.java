package io.github.elmergj.movish.api.domain.shared;

import java.time.LocalDateTime;

public interface Event {
    default LocalDateTime occurredOn(){
        return LocalDateTime.now();
    }
}
