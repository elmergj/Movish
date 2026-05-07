package io.github.elmergj.movish.api.domain.shared;

import io.github.elmergj.movish.api.domain.exception.ValidationException;

import java.util.Objects;

public final class Ensure<T> {

    private final T value;
    private final String fieldName;

    private Ensure(T value, String fieldName) {
        this.value = value;
        this.fieldName = fieldName;
    }

    /**@param value must be the value to be validated.*/
    public static <T> Ensure<T> that(T value , String fieldName) {
        return new Ensure<>(value, fieldName);
    }

    public Ensure<T> isNotBlank() {
        if  (value == null || String.valueOf(value).isBlank()) {
            throw new ValidationException("WARNING | " + fieldName + " is required");
        }
        return this;
    }

    public Ensure<T> minLength(int length) {
        if (value != null && Objects.toString(value).length() < length) {
            throw new ValidationException(fieldName + " must be at least " + length + " characters");
        }
        return this;
    }

    public Ensure<T> maxLength(int length) {
        if (value != null && Objects.toString(value).length() > length) {
            throw new ValidationException(fieldName + " must be at most " + length + " characters");
        }
        return this;
    }

    public T getValue() {
        return value;
    }
}