package io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers;

import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.Watchlist;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.WatchlistId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.TitleListEntity;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.UserEntity;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TitleListJpaMapper {

    public @NonNull TitleListEntity toJpaTitleList(Watchlist watchlist){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(watchlist.getUserOwnerId().value());

        TitleListEntity titleListEntity = new TitleListEntity();
        titleListEntity.setUserEntity(userEntity);

        titleListEntity.setId(watchlist.id().value());
        titleListEntity.setName(watchlist.getName());
        titleListEntity.setDateCreated(watchlist.getDateCreated());
        titleListEntity.setListType(watchlist.getListType());
        titleListEntity.setUserTitleIds(watchlist.getUserTitleIdReferences().stream()
                .map(TitleId::value)
                .collect(Collectors.toSet())
        );

        return titleListEntity;
    }

    public Watchlist toDomain(TitleListEntity jpaTitleList){
        return Watchlist.fromExisting(
                WatchlistId.from(jpaTitleList.getId()),
                UserId.from(jpaTitleList.getUserEntity().getId()),
                jpaTitleList.getName(),
                jpaTitleList.getDateCreated(),
                jpaTitleList.getListType(),
                //Passing a mutable list
                jpaTitleList.getUserTitleIds().stream().map(TitleId::from)
                        .collect(Collectors.toSet())
        );
    }
}
