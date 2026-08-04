package io.github.elmergj.movish.api.domain.repository;

import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.Watchlist;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.WatchlistId;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.WatchlistType;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;

import java.util.Optional;

public interface WatchlistRepository {

    void save(Watchlist list);

    Optional<Watchlist> findById(WatchlistId id);

    Optional<Watchlist> findByIdAndUserOwnerId(WatchlistId id, UserId userId);

    Optional<Watchlist> findByUserOwnerId(UserId userId);

    void removeReferenceFromAllLists(TitleId titleId);

    Optional<Watchlist> findByUserOwnerIdAndListType(UserId userId, WatchlistType watchlistType);

    boolean existByUserOwnerIdAndListName(UserId userId, String name);

    void delete(Watchlist watchlist);
}
