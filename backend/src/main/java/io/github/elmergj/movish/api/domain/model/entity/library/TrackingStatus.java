package io.github.elmergj.movish.api.domain.model.entity.library;

public enum TrackingStatus{
    NOT_TRACKED(0),
    TO_WATCH(1),
    WATCHING(2),
    WATCHED(3),
    STOPPED(4),
    ;

    private final int code;

    TrackingStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TrackingStatus fromCode(int code) {
        for (TrackingStatus status : TrackingStatus.values()) {
            if (status.code == code) {
                return status;
            }
        }

        throw new IllegalArgumentException("Invalid tracking status code: " + code);
    }



}
