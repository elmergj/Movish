package io.github.elmergj.movish.api.domain.model.entity.catalog.media;

public enum MediaProvider{

    TMDB(
            MediaProviderId.from("prov01"),
            "The Movie Database",
            "tmdb"),

    OMDB(
            MediaProviderId.from("prov02"),
            "Open Movie Database",
            "omdb"),

    IMDB(
            MediaProviderId.from("prov03"),
            "Internet Movie Database",
            "imdb");

    private final MediaProviderId mediaProviderId;
    private final String fullName;
    private final String externalName;

    MediaProvider(MediaProviderId mediaProviderId, String fullName, String externalName) {
        this.mediaProviderId = mediaProviderId;
        this.fullName = fullName;
        this.externalName = externalName;
    }

    public String getFullName() {
        return fullName;
    }

    public static MediaProvider fromExternalName(String value) {
        return MediaProvider.valueOf(value.toUpperCase());
    }

    public MediaProviderId getMediaProviderId() {
        return mediaProviderId;
    }

    public String getExternalName() {
        return externalName;
    }
}
