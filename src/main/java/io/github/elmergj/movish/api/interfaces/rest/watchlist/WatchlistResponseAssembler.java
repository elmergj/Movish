package io.github.elmergj.movish.api.interfaces.rest.watchlist;

import io.github.elmergj.movish.api.application.listing.query.WatchlistDetailsView;
import io.github.elmergj.movish.api.interfaces.rest.watchlist.WatchlistDetailsResponse.WatchlistItem;
import org.springframework.stereotype.Component;

@Component
public class WatchlistResponseAssembler {

    WatchlistDetailsResponse assemble(WatchlistDetailsView view) {
        return new WatchlistDetailsResponse(
                view.id(),
                view.name(),
                view.totalItems(),
                view.watchlistItemDetails().stream()
                        .map(item -> new WatchlistItem(
                                item.titleId(),
                                item.trackingStatus(),
                                item.dateAdded(),
                                item.mediaType()
                        ))
                        .toList()
        );
    }
}
