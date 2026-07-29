package io.github.elmergj.movish.api.domain.model.entity.catalog.media;

import io.github.elmergj.movish.api.domain.exception.ValidationException;
import io.github.elmergj.movish.api.domain.shared.Ensure;

import java.text.DecimalFormat;

public record MediaAverageRating(
        double averageRating,
        String provider,
        int votesCount,
        RatingScale scale
) {

    // Compact constructor: always executed, ensuring validation and formatting
     public MediaAverageRating {
        validateVotesCount(votesCount);
        if (scale == null) {
            scale = RatingScale.ONE_TO_TEN;
        }
        averageRating = formatValue(averageRating, scale);
    }

    // Factory Methods with default scale (ONE_TO_TEN)
    public static MediaAverageRating of(double rating, String provider, int votesCount) {
        return new MediaAverageRating(rating, provider, votesCount, RatingScale.ONE_TO_TEN);
    }

    public static MediaAverageRating of(String rating, String provider, int votesCount) {
        Ensure.that(rating, "MediaAverageRating").isNotBlank();
        return new MediaAverageRating(Double.parseDouble(rating), provider, votesCount, RatingScale.ONE_TO_TEN);
    }

    public static MediaAverageRating of(double rating, String provider, int votesCount, RatingScale scale) {
        return new MediaAverageRating(rating, provider, votesCount, scale);
    }

    public static MediaAverageRating of(String rating, String provider, int votesCount, RatingScale scale) {
        Ensure.that(rating, "MediaAverageRating").isNotBlank();
        return new MediaAverageRating(Double.parseDouble(rating), provider, votesCount, scale);
    }
    // Todo: Complete Domain Validation Exceptions to Result Pattern mapping

    /**
     * Formats the rating value.
     * <p>
     * <b>Note:</b> Can be extended in the future if a provider uses a different scale (e.g., 1 to 100).
     */
    private static double formatValue(double rating, RatingScale scale) {
        validateValueRange(rating, scale);

        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return Double.parseDouble(decimalFormat.format(rating));
    }

    private static void validateValueRange(double rating, RatingScale scale) {
        switch (scale) {
            case ONE_TO_TEN:
                if (rating < 1.0 || rating > 10.0) {
                    throw new ValidationException("MediaAverageRating must be between 1.0 and 10.0");
                }
                break;
            default:
                throw new ValidationException("Unsupported rating scale");
        }
    }

    private static void validateVotesCount(int votesCount) {
        if (votesCount < 0) {
            throw new ValidationException("Votes count cannot be negative");
        }
    }

    public double value() {
        return averageRating;
    }

    /**
     * Represents the rating scale used by the provider.
     * <p>
     * Can be expanded with more scales in the future (e.g., ONE_TO_ONE_HUNDRED).
     */
    public enum RatingScale {
        ONE_TO_TEN
    }
}