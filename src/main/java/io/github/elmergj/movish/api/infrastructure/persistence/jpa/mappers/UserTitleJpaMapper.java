package io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaId;
import io.github.elmergj.movish.api.domain.model.entity.library.Title;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleReview;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleUserRating;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.UserEntity;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.TitleEntity;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class UserTitleJpaMapper {

    public @Nonnull TitleEntity toJpaUserTitle(Title title) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(title.getUserOwnerId().value());

        TitleEntity titleEntity = new TitleEntity();
        titleEntity.setUserEntity(userEntity);

        titleEntity.setId(title.id().value());
        titleEntity.setMediaId(title.getMediaId().value());
        titleEntity.setCreatedDate(title.getDateAdded());
        titleEntity.setFavorite(title.isFavorite());
        titleEntity.setStatus(title.getTrackingStatus());
        titleEntity.setTimesWatched(title.getTimesWatched());
        titleEntity.setTitleUserRating(title.getUserRating().value());
        titleEntity.setUserTitleReview(title.getUserReview().value());


        return titleEntity;
    }

    public Title toDomain(TitleEntity jpaUserTitle){
        return Title.fromExisting(
                TitleId.from(jpaUserTitle.getId()),
                MediaId.from(jpaUserTitle.getMediaId()),
                UserId.from(jpaUserTitle.getUserEntity().getId()),
                jpaUserTitle.isFavorite(),
                jpaUserTitle.getCreatedDate(),
                jpaUserTitle.getStatus(),
                jpaUserTitle.getTimesWatched(),
                TitleUserRating.of(jpaUserTitle.getTitleUserRating()),
                TitleReview.from(jpaUserTitle.getUserTitleReview())
        );
    }
}
