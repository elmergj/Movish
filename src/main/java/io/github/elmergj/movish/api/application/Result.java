package io.github.elmergj.movish.api.application;

/**
 * Represents the result of a command execution that has explicit success and
 * failure outcomes.
 *
 * <p>
 * A {@code Result} belongs to the {@link CommandResult} family and is intended
 * for commands where multiple business outcomes must be represented explicitly.
 * The operation can either complete successfully or fail due to an expected
 * business condition.
 * </p>
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
 * <p>This means that an operation can either:</p>
 *
 * <ul>
 *     <li>Return a successful outcome such as {@code BookAdded}.</li>
 *     <li>Return a business failure such as {@code BookAdditionFailure}.</li>
 * </ul>
 *
 * <p>
 * Simpler commands that do not require explicit failure modeling may implement
 * {@link CommandResult} directly with their own concrete outcome type.
 * </p>
 *
 * <p>
 * Domain rules should not depend directly on this abstraction. The Application
 * layer is responsible for translating domain outcomes into command results
 * when explicit success and failure modeling is required.
 * </p>
 *
 * @param <S> the type representing a successful outcome
 * @param <F> the type representing an expected failure
 */
public sealed interface Result<
        S extends Result.SuccessOutcome,
        F extends Result.FailureReason>
        extends CommandResult {

    /**
     * Marker interface for successful outcomes.
     *
     * <p>
     * Implementations represent the positive result of an application
     * operation.
     * </p>
     */
    interface SuccessOutcome {
    }


    /**
     * Marker interface for expected failures.
     *
     * <p>
     * Implementations represent business conditions where the operation cannot
     * be completed.
     * </p>
     */
    interface FailureReason {
    }


    /**
     * Creates a successful Result containing the given outcome.
     *
     * <p>
     * This factory method provides a more expressive alternative to directly
     * instantiating {@link SuccessResult}.
     * </p>
     *
     * @param value the successful outcome
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
     * Creates a failed Result containing the given failure reason.
     *
     * <p>
     * This factory method provides a more expressive alternative to directly
     * instantiating {@link FailureResult}.
     * </p>
     *
     * @param value the failure information
     * @param <S> the type of the success outcome
     * @param <F> the type of the expected failure
     * @return a failed Result containing the provided failure reason
     */
    static <
            S extends SuccessOutcome,
            F extends FailureReason>
    Result<S, F> failure(F value) {
        return new FailureResult<>(value);
    }


    /**
     * Represents a successful command execution.
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
     * Represents a failed command execution.
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