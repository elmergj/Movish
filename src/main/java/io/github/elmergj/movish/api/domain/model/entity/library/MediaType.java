package io.github.elmergj.movish.api.domain.model.entity.library;

public enum MediaType  {
    MOVIE("movie"),
    TV("tv");

    public static MediaType fromExternalValue(String value) {
        return MediaType.valueOf(value.toUpperCase());
    }

    private final String externalValue;

    MediaType(String externalValue) {
        this.externalValue = externalValue;
    }

    public String externalValue() {
        return externalValue;
    }
}

