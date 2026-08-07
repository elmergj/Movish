package io.github.elmergj.movish.api.interfaces.rest.watchlist;

import java.util.List;

public record WatchlistDetailsResponse(
        String id,
        String listName,
        int totalItems,
        List<WatchlistItem> items
) {
    public record WatchlistItem(
            String titleId,
            String trackingStatus,
            String dateAdded,
            String mediaType
    ){}
}
