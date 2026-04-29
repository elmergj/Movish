package io.github.elmergj.movish.api.domain.shared;

import java.util.Objects;

public abstract class BaseEntity<E extends BaseEntity<E, ID>, ID extends BaseId> implements Entity<E, ID> {

    protected final ID id;

    protected BaseEntity(ID id) {
        this.id = Objects.requireNonNull(id, "The entity ID cannot be null");
    }

    @Override
    public boolean sameIdentityAs(E other) {
        if (this == other) return true;
        return other != null && Objects.equals(this.id, other.id());
    }

    @Override
    public ID id() {
        return id;
    }

    @Override
    public final boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        BaseEntity<?, ?> that = (BaseEntity<?, ?>) other;
        return Objects.equals(id, that.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
