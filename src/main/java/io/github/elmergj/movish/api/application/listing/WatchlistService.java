package io.github.elmergj.movish.api.application.listing;

import io.github.elmergj.movish.api.application.listing.command.AddTitleToWatchlistCommand;
import io.github.elmergj.movish.api.application.listing.command.CreateWatchlistCommand;
import io.github.elmergj.movish.api.application.listing.command.DeleteWatchlistCommand;
import io.github.elmergj.movish.api.application.listing.command.RemoveTitleFromWatchlistCommand;
import io.github.elmergj.movish.api.application.listing.command.TitleAdditionToWatchlistOutcome;
import io.github.elmergj.movish.api.application.listing.command.TitleRemovalFromWatchlistOutcome;
import io.github.elmergj.movish.api.application.listing.command.UpdateWatchlistNameCommand;
import io.github.elmergj.movish.api.application.listing.command.WatchlistCreationOutcome;
import io.github.elmergj.movish.api.application.listing.command.WatchlistDeletionOutcome;
import io.github.elmergj.movish.api.application.listing.command.WatchlistNameUpdateOutcome;
import io.github.elmergj.movish.api.application.listing.query.WatchlistDetailsQuery;
import io.github.elmergj.movish.api.application.listing.query.WatchlistDetailsView;
import io.github.elmergj.movish.api.application.listing.query.WatchlistDetailsView.WatchlistItem;
import io.github.elmergj.movish.api.application.listing.query.WatchlistOverviewQuery;
import io.github.elmergj.movish.api.application.listing.query.WatchlistOverviewView;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.CustomListFactory;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.Watchlist;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.WatchlistId;
import io.github.elmergj.movish.api.domain.repository.TitleRepository;
import io.github.elmergj.movish.api.domain.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final TitleRepository titleRepository;
    private final CustomListFactory customListFactory;

    @Transactional
    public WatchlistCreationOutcome createCustomWatchlist(CreateWatchlistCommand command){

        Watchlist watchlist = customListFactory.create(
                UserId.from(command.userId()),
                command.name());

        watchlistRepository.save(watchlist);

        return new WatchlistCreationOutcome(
                watchlist.id().value(),
                watchlist.getName(),
                watchlist.getDateCreated().toString()
        );
    }

    public WatchlistOverviewView getWatchlistOverview(WatchlistOverviewQuery query){

        Watchlist watchlist = watchlistRepository.findByIdAndUserOwnerId(
                WatchlistId.from(query.titleListId()), UserId.from(query.userId()))
                .orElseThrow();

        return new WatchlistOverviewView(
                watchlist.id().value(),
                watchlist.getName(),
                watchlist.getTitleIdReferences().size()
        );
    }

    @Transactional
    public WatchlistDeletionOutcome deleteWatchlist(DeleteWatchlistCommand command){

        Watchlist watchlist = watchlistRepository.findByIdAndUserOwnerId(
                WatchlistId.from(command.customListId()), UserId.from(command.userId()))
                .orElseThrow();

        watchlist.canBeDeleted();

        watchlistRepository.delete(watchlist);

        return new WatchlistDeletionOutcome(
                watchlist.id().value(),
                watchlist.getName()
        );
    }

    @Transactional
    public TitleAdditionToWatchlistOutcome addTitleToList(AddTitleToWatchlistCommand command){

        Watchlist watchlist = watchlistRepository.findByIdAndUserOwnerId(
                        WatchlistId.from(command.customListId()), UserId.from(command.userId()))
                .orElseThrow();

        watchlist.addTitle(TitleId.from(command.titleId()));

        watchlistRepository.save(watchlist);

        return new TitleAdditionToWatchlistOutcome(
                watchlist.id().value(),
                command.titleId(),
                watchlist.getTitleIdReferences().size()
        );
    }


    public WatchlistDetailsView getListItemsDetails(WatchlistDetailsQuery query){
        var watchlist = watchlistRepository.findByIdAndUserOwnerId(
                        WatchlistId.from(query.titleListId()),
                        UserId.from(query.userId()))
                .orElseThrow();


        var watchlistItems = titleRepository.findAllByIds(watchlist.getTitleIdReferences()).stream()
                .map(title -> new WatchlistItem(
                        title.id().value(),
                        title.getTrackingStatus().externalValue(),
                        title.getDateAdded().toString(),
                        title.getMediaType().externalValue()
                ))
                .toList();

        return new WatchlistDetailsView(
                watchlist.id().value(),
                watchlist.getName(),
                watchlist.getTitleIdReferences().size(),
                watchlistItems
        );
    }

    @Transactional
    public TitleRemovalFromWatchlistOutcome removeTitleFromList(RemoveTitleFromWatchlistCommand command){

        Watchlist watchlist = watchlistRepository.findByIdAndUserOwnerId(
                        WatchlistId.from(command.customListId()), UserId.from(command.userId()))
                .orElseThrow();


        watchlist.removeTitle(TitleId.from(command.titleId()));

        watchlistRepository.save(watchlist);

        return new TitleRemovalFromWatchlistOutcome(
                watchlist.id().value(),
                command.titleId(),
                watchlist.getTitleIdReferences().size()
        );
    }

    @Transactional
    public WatchlistNameUpdateOutcome updateCustomTitleListName(UpdateWatchlistNameCommand command){

        Watchlist watchlist = watchlistRepository.findByIdAndUserOwnerId(
                        WatchlistId.from(command.customListId()), UserId.from(command.userId()))
                .orElseThrow();

        watchlist.updateListName(command.newName());

        watchlistRepository.save(watchlist);

        return new WatchlistNameUpdateOutcome(
                watchlist.id().value(),
                watchlist.getName(),
                watchlist.getTitleIdReferences().size());
    }
}