package io.github.elmergj.movish.api.application.catalog;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaProvider;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaProviderId;
import org.springframework.stereotype.Component;

@Component
public class EnumMediaProviderRegistry implements MediaProviderRegistry {

    @Override
    public MediaProviderId findByExternalName(String externalName) {
        return switch(externalName){
            case "tmdb" -> MediaProvider.TMDB.getMediaProviderId();
            case "omdb" -> MediaProvider.OMDB.getMediaProviderId();
            case "imdb" -> MediaProvider.IMDB.getMediaProviderId();
            default -> throw new IllegalStateException("Unexpected value: " + externalName);
        };
    }

    @Override
    public MediaProvider findById(MediaProviderId id) {
        return switch(id.value()) {
            case "prov01" -> MediaProvider.TMDB;
            case "prov02" -> MediaProvider.OMDB;
            case "prov03" -> MediaProvider.IMDB;
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

    @Override
    public String findExternalNameById(MediaProviderId id) {
        return switch(id.value()) {
            case "prov01" -> MediaProvider.TMDB.getExternalName();
            case "prov02" -> MediaProvider.OMDB.getExternalName();
            case "prov03" -> MediaProvider.IMDB.getExternalName();
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }
}