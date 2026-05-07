package io.github.elmergj.movish.api.domain.shared;

import java.util.function.Function;

public interface EntityIdGenerator{

    String nextId();

    default <T extends BaseId> T generate(Function<String, T> factory) {
        return factory.apply(this.nextId());
    }
}
