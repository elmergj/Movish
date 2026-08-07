package io.github.elmergj.movish.api.domain.model.entity.library;

public enum TrackingStatus{
    NOT_TRACKED(0, "not_tracked"),
    TO_WATCH(1, "to_watch"),
    WATCHING(2, "watching"),
    WATCHED(3, "watched"),
    STOPPED(4, "stopped"),
    ;

    private final int code;
    private final String externalValue;

    TrackingStatus(int code, String externalValue) {
        this.code = code;
        this.externalValue = externalValue;
    }

    public int getCode() {
        return code;
    }

    public String externalValue() {
        return externalValue;
    }


    public static TrackingStatus fromCode(int code) {
        for (TrackingStatus status : TrackingStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid tracking status code: " + code);
    }

    public static TrackingStatus fromExternalValue(String externalValue) {
        for (TrackingStatus status : TrackingStatus.values()) {
            if (status.externalValue.equals(externalValue)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid tracking status externalValue: " + externalValue);
    }
}
