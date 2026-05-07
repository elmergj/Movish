package io.github.elmergj.movish.api.domain.model.entity.catalog.title;

import io.github.elmergj.movish.api.domain.exception.ValidationException;
import io.github.elmergj.movish.api.domain.shared.Ensure;

import java.text.DecimalFormat;

public class TitleRating {

    private final double value;

    private TitleRating(double value) {
        this.value = value;
    }

    //Factory Methods
    public static TitleRating of(String rating) {
        Ensure.that(rating, "TitleRating").isNotBlank();
        return new TitleRating(formatValue(Double.parseDouble(rating)));
    }

    public static TitleRating of(double rating) {
        Ensure.that(rating, "TitleRating").isNotBlank();
        return new TitleRating(formatValue(rating));
    }

    //Support Methods
    private static double formatValue(double rating) {
        validateValueRange(rating);
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return Double.parseDouble(decimalFormat.format(rating));
    }

    private static void validateValueRange(double rating) {
        if (rating < 1.0 || rating > 10.0) {
            throw new ValidationException("TitleRating must be between 1.0 and 10.0");
        }
    }

    public double value() {
        return value;
    }
}
