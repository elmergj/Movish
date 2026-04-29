package io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers;

import io.github.elmergj.movish.api.domain.model.entity.catalog.title.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.TitleReview;
import io.github.elmergj.movish.api.domain.model.entity.library.UserTitle;
import io.github.elmergj.movish.api.domain.model.entity.library.UserTitleId;
import io.github.elmergj.movish.api.domain.model.entity.library.UserTitleRating;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.UserEntity;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.UserTitleEntity;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class UserTitleJpaMapper {

    public @Nonnull UserTitleEntity toJpaUserTitle(UserTitle userTitle) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userTitle.getUserOwnerId().value());

        UserTitleEntity userTitleEntity = new UserTitleEntity();
        userTitleEntity.setUserEntity(userEntity);

        userTitleEntity.setId(userTitle.id().value());
        userTitleEntity.setTitleId(userTitle.getTitleId().value());
        userTitleEntity.setCreatedDate(userTitle.getDateAdded());
        userTitleEntity.setFavorite(userTitle.isFavorite());
        userTitleEntity.setStatus(userTitle.getTrackingStatus());
        userTitleEntity.setTimesWatched(userTitle.getTimesWatched());
        userTitleEntity.setUserTitleRating(userTitle.getUserRating().value());
        userTitleEntity.setUserTitleReview(userTitle.getUserReview().value());


        return userTitleEntity;
    }

    public UserTitle toDomain(UserTitleEntity jpaUserTitle){
        return UserTitle.fromExisting(
                UserTitleId.from(jpaUserTitle.getId()),
                TitleId.from(jpaUserTitle.getTitleId()),
                UserId.from(jpaUserTitle.getUserEntity().getId()),
                jpaUserTitle.isFavorite(),
                jpaUserTitle.getCreatedDate(),
                jpaUserTitle.getStatus(),
                jpaUserTitle.getTimesWatched(),
                UserTitleRating.of(jpaUserTitle.getUserTitleRating()),
                TitleReview.from(jpaUserTitle.getUserTitleReview())
        );
    }
}
