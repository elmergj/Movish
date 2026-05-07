package io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers;

import io.github.elmergj.movish.api.domain.model.entity.catalog.title.Title;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.TitleRating;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.TitleEntity;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TitleJpaMapper {

    public @NonNull TitleEntity toJpaTitle(Title title) {
        TitleEntity titleEntity = new TitleEntity();

        titleEntity.setId(title.id().value());
        titleEntity.setExternalTitleId(title.externalId());
        titleEntity.setName(title.name());
        titleEntity.setReleaseDate(title.releaseDate());
        titleEntity.setTmdbRating(title.tmdbTitleRating().value());
        titleEntity.setMediaType(title.mediaType());

        return titleEntity;
    }

    public Title toDomain(TitleEntity titleEntity){
        return new Title(
                TitleId.from(titleEntity.getId()),
                titleEntity.getExternalTitleId(),
                titleEntity.getName(),
                titleEntity.getReleaseDate(),
                TitleRating.of(titleEntity.getTmdbRating()),
                titleEntity.getMediaType()
        );
    }
}
