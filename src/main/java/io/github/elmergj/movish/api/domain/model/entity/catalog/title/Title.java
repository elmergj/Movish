
package io.github.elmergj.movish.api.domain.model.entity.catalog.title;

import java.time.LocalDate;

public record Title(
        TitleId id,
        String externalId,
        MediaTypeDetails mediaTypeDetails,
        String name,
        LocalDate releaseDate,
        MediaType mediaType,
        String imdbId,
        TitleRating imdbTitleRating,
        TitleRating tmdbTitleRating,
        TitleGenre[] genres) {

    //Bug: solve this, complete the TitleEntity modeling so this is not necessary
    public Title(TitleId id, String externalId, String name, LocalDate releaseDate, TitleRating tmdbTitleRating,
                 MediaType mediaType){
        this(id, externalId, null, name, releaseDate, mediaType, null, null, tmdbTitleRating, null);
    }

}
