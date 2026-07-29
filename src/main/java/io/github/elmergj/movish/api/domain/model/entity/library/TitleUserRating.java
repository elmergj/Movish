package io.github.elmergj.movish.api.domain.model.entity.library;

import io.github.elmergj.movish.api.domain.exception.ValidationException;
import io.github.elmergj.movish.api.domain.shared.Ensure;

public class TitleUserRating {

    private final int value;

    private TitleUserRating(int value) {
        this.value = value;
    }

    public static TitleUserRating of(int userRating) {
        Ensure.that(userRating, "User rating")
                .isNotBlank();
        validateValueRange(userRating);
        return new TitleUserRating(userRating);
    }

    private static void validateValueRange(int userRating) {
        if (userRating < 1 || userRating > 10) {
            throw new ValidationException("The rate must be between 1 and 10");
        }
    }

    public int value() {
        return value;
    }
}
