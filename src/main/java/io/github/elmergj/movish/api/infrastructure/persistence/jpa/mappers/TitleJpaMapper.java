package io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers;

import io.github.elmergj.movish.api.domain.model.entity.library.MediaId;
import io.github.elmergj.movish.api.domain.model.entity.library.Title;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleReview;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleUserRating;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.UserEntity;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.TitleEntity;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TitleJpaMapper {

    public @Nonnull TitleEntity toJpaTitle(Title title) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(title.getUserOwnerId().value());

        TitleEntity titleEntity = new TitleEntity();
        titleEntity.setUserEntity(userEntity);

        titleEntity.setId(title.id().value());
        titleEntity.setMediaId(title.getMediaId().value());
        titleEntity.setMediaType(title.getMediaType());
        titleEntity.setName(title.getName());

        titleEntity.setCreatedDate(title.getDateAdded());
        titleEntity.setFavorite(title.isFavorite());
        titleEntity.setStatus(title.getTrackingStatus());
        titleEntity.setTimesWatched(title.getTimesWatched());
        titleEntity.setTitleUserRating(
                title.getUserRating()
                        .map(TitleUserRating::value)
                        .orElse(null));
        titleEntity.setTitleReview(title.getUserReview().value());


        return titleEntity;
    }

    public Title toDomain(TitleEntity jpaTitle){
        return Title.fromExisting(
                TitleId.from(jpaTitle.getId()),
                MediaId.from(jpaTitle.getMediaId()),
                jpaTitle.getName(),
                jpaTitle.getMediaType(),
                UserId.from(jpaTitle.getUserEntity().getId()),
                jpaTitle.isFavorite(),
                jpaTitle.getCreatedDate(),
                jpaTitle.getStatus(),
                jpaTitle.getTimesWatched(),
                Optional.ofNullable(jpaTitle.getTitleUserRating()   )
                        .map(TitleUserRating::of)
                        .orElse(null),
                TitleReview.from(jpaTitle.getTitleReview())
        );
    }
}
