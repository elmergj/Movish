package io.github.elmergj.movish.api.domain.shared;

import java.util.Objects;

public abstract class BaseId {

    private final String value;

    protected BaseId(String value) {
        Ensure.that(value, "id").isNotBlank();

        this.value = value;
    }

    public String value() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseId that = (BaseId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
