package io.github.elmergj.movish.api.domain.shared;

/**
 * A simple generic container representing either a success or a failure.
 *
 * <p>This class is useful for returning values from operations that can fail
 * without using exceptions. A successful Result contains a value of type T,
 * while a failed Result contains an error of type E.</p>
 *
 * @param <T> the type of the success value
 * @param <E> the type of the error value
 */
public class Result<T, E> {
    private final T value;
    private final E error;
    private final boolean success;

    /**
     * Private constructor to initialize a Result.
     *
     * @param value the success value, null if failure
     * @param error the error value, null if success
     * @param success true if this is a success, false if failure
     */
    private Result(T value, E error, boolean success) {
        this.value = value;
        this.error = error;
        this.success = success;
    }

    /**
     * Creates a successful Result containing the given value.
     *
     * @param value the success value
     * @param <T> type of the success value
     * @param <E> type of the error value
     * @return a Result representing success
     */
    public static <T, E> Result<T, E> success(T value) {
        return new Result<>(value, null, true);
    }

    /**
     * Creates a failed Result containing the given error.
     *
     * @param error the error value
     * @param <T> type of the success value
     * @param <E> type of the error value
     * @return a Result representing failure
     */
    public static <T, E> Result<T, E> failure(E error) {
        return new Result<>(null, error, false);
    }

    /**
     * Checks if this Result is a success.
     *
     * @return true if this is a success, false if failure
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the success value.
     *
     * @return the success value if present, null if failure
     */
    public T getValue() {
        return value;
    }

    /**
     * Gets the error value.
     *
     * @return the error value if present, null if success
     */
    public E getError() {
        return error;
    }
}


