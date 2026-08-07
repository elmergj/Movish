package io.github.elmergj.movish.api.application.listing.query;

import java.util.List;

public record WatchlistDetailsView(
        String id,
        String name,
        int totalItems,
        List<WatchlistItem> watchlistItemDetails
) {
    public record WatchlistItem(
            String titleId,
            String trackingStatus,
            String dateAdded,
            String mediaType
    ) {
    }
}