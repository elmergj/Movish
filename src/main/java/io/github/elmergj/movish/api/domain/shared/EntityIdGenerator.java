package io.github.elmergj.movish.api.domain.shared;

import java.util.function.Function;

/**
 * Generates application-level entity identifiers.
 *
 * <p>Implementations are responsible for producing identifiers that satisfy
 * the uniqueness guarantees required by the application.
 *
 * <p>This abstraction decouples identifier generation from persistence
 * concerns such as database sequences, auto-increment columns, UUIDs,
 * ULIDs, or custom strategies.
 */
public interface EntityIdGenerator{

    /**
     * Generates a new identifier value.
     *
     * @return a newly generated identifier
     */
    String nextId();

    /**
     * Generates an identifier and converts it into a domain-specific
     * {@link BaseId} value object.
     *
     * <p>Example:
     *
     * <pre>
     * UserId userId = generator.generate(UserId::new);
     * </pre>
     *
     * @param factory function used to create the target identifier type
     * @param <T> concrete {@link BaseId} subtype
     * @return a newly generated domain identifier
     */
    default <T extends BaseId> T generate(Function<String, T> factory) {
        return factory.apply(this.nextId());
    }
}
