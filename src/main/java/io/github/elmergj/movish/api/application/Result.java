package io.github.elmergj.movish.api.application;

/**
 * Represents the result of an application operation.
 *
 * <p>A Result has only two possible states:</p>
 *
 * <ul>
 *     <li>{@link SuccessResult}: the operation completed successfully.</li>
 *     <li>{@link FailureResult}: the operation failed due to an expected business condition.</li>
 * </ul>
 *
 * <p>The type parameters enforce that:</p>
 *
 * <ul>
 *     <li>{@code S} belongs to the {@link SuccessOutcome} family.</li>
 *     <li>{@code F} belongs to the {@link FailureReason} family.</li>
 * </ul>
 *
 * <p>This means that the operation can either:</p>
 *
 * <ul>
 *     <li>Return {@code BookAdded} when successful.</li>
 *     <li>Return {@code BookAdditionFailure} when rejected.</li>
 * </ul>
 *
 * <p>
 * Domain rules should not depend on this abstraction.
 * The Application layer is responsible for translating
 * domain outcomes into Result values.
 * </p>
 *
 * @param <S> the type representing a successful outcome
 * @param <F> the type representing an expected failure
 */
public sealed interface Result<
        S extends Result.SuccessOutcome,
        F extends Result.FailureReason> {

    /**
     * Marker interface for successful outcomes.
     *
     * <p>
     * Implementations represent the positive result
     * of an application operation.
     * </p>
     */
    interface SuccessOutcome{
    }


    /**
     * Marker interface for expected failures.
     *
     * <p>
     * Implementations represent business conditions
     * where the operation cannot be completed.
     * </p>
     */
    interface FailureReason {
    }

    /**
     * Represents the absence of an expected failure.
     *
     * <p>
     * This marker value is used when an application operation
     * cannot produce a business failure represented by a
     * {@link FailureReason}.
     * </p>
     *
     * <p>
     * It allows the {@link Result} contract to remain consistent
     * by providing a failure type for operations that only have
     * a successful outcome.
     * </p>
     *
     * <pre>
     * Result&lt;OperationOutcome, Result.NoFailure&gt;
     * </pre>
     */
    enum NoFailure implements FailureReason {
        INSTANCE
    }

    /**
     * Creates a successful Result containing the given outcome.
     *
     * <p>
     * This factory method provides a more expressive alternative
     * to directly instantiating {@link SuccessResult}.
     * </p>
     *
     * @param value the successful outcome outcome
     * @param <S> the type of the success outcome
     * @param <F> the type of the expected failure
     * @return a successful Result containing the provided outcome
     */
    static <
            S extends SuccessOutcome,
            F extends FailureReason>
    Result<S, F> success(S value) {
        return new SuccessResult<>(value);
    }

    /**
     * Creates a failed Result containing the given value.
     *
     * <p>
     * This factory method provides a more expressive alternative
     * to directly instantiating {@link FailureResult}.
     * </p>
     *
     * @param value the failure information
     * @param <S> the type of the success outcome
     * @param <F> the type of the expected value
     * @return a failed Result containing the provided value
     */
    static <
            S extends SuccessOutcome,
            F extends FailureReason>
    Result<S, F> failure(F value) {
        return new FailureResult<>(value);
    }

    /**
     * Represents a successful operation.
     *
     * <p>
     * Contains the outcome produced by the operation.
     * </p>
     *
     * @param outcome the object representing the successful operation outcome
     * @param <S> the type of the success outcome
     * @param <F> the type of the expected failure
     */
    record SuccessResult<
            S extends SuccessOutcome,
            F extends FailureReason>(
                    S outcome
    ) implements Result<S, F> {
    }

    /**
     * Represents a failed operation.
     *
     * <p>
     * Contains the expected failure information.
     * </p>
     *
     * @param reason the failure information
     * @param <S> the type of the success outcome
     * @param <F> the type of the expected failure
     */
    record FailureResult<
            S extends SuccessOutcome,
            F extends FailureReason>(
                    F reason
    ) implements Result<S, F> {
    }
}