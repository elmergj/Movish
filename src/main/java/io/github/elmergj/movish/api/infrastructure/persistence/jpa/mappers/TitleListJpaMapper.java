package io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers;

import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.Watchlist;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.WatchlistId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.WatchlistEntity;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.UserEntity;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TitleListJpaMapper {

    public @NonNull WatchlistEntity toJpaTitleList(Watchlist watchlist){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(watchlist.getUserOwnerId().value());

        WatchlistEntity watchlistEntity = new WatchlistEntity();
        watchlistEntity.setUserEntity(userEntity);

        watchlistEntity.setId(watchlist.id().value());
        watchlistEntity.setName(watchlist.getName());
        watchlistEntity.setDateCreated(watchlist.getDateCreated());
        watchlistEntity.setListType(watchlist.getListType());
        watchlistEntity.setTitleIds(watchlist.getTitleIdReferences().stream()
                .map(TitleId::value)
                .collect(Collectors.toSet())
        );

        return watchlistEntity;
    }

    public Watchlist toDomain(WatchlistEntity jpaTitleList){
        return Watchlist.fromExisting(
                WatchlistId.from(jpaTitleList.getId()),
                UserId.from(jpaTitleList.getUserEntity().getId()),
                jpaTitleList.getName(),
                jpaTitleList.getDateCreated(),
                jpaTitleList.getListType(),
                //Passing a mutable list
                jpaTitleList.getTitleIds().stream().map(TitleId::from)
                        .collect(Collectors.toSet())
        );
    }
}
