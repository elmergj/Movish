package io.github.elmergj.movish.api.domain.model.entity.library;

public enum MediaType  {
    MOVIE("movie"),
    TV("tv");

    private final String externalValue;

    MediaType(String externalValue) {
        this.externalValue = externalValue;
    }

    public String externalValue() {
        return externalValue;
    }

    public static MediaType fromExternalValue(String value) {
        for (MediaType mediaType : values()) {
            if (mediaType.externalValue.equals(value)) {
                return mediaType;
            }
        }
        throw new IllegalArgumentException("Invalid media type: " + value);
    }
}

