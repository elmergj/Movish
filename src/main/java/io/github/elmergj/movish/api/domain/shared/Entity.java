package io.github.elmergj.movish.api.domain.shared;

public interface Entity<E, ID extends BaseId> {

    boolean sameIdentityAs(E other);

    ID id();
}
