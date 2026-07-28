package io.github.elmergj.movish.api.domain.model.entity.watchlist;

import io.github.elmergj.movish.api.domain.shared.BaseId;

public class WatchlistId extends BaseId {

    private WatchlistId(String id) {
        super(id);
    }

    public static WatchlistId from(String id) {
        return new WatchlistId(id);
    }
}
