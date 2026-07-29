package io.github.elmergj.movish.api.application.listing;

import io.github.elmergj.movish.api.application.listing.command.AddTitleToListCommand;
import io.github.elmergj.movish.api.application.listing.command.UpdateCustomListNameCommand;
import io.github.elmergj.movish.api.application.listing.command.CreateCustomTitleListCommand;
import io.github.elmergj.movish.api.application.listing.command.CustomListCreationOutcome;
import io.github.elmergj.movish.api.application.listing.command.CustomListDeletionOutcome;
import io.github.elmergj.movish.api.application.listing.command.DeleteCustomListCommand;
import io.github.elmergj.movish.api.application.listing.command.ListNameUpdateOutcome;
import io.github.elmergj.movish.api.application.listing.command.RemoveTitleFromListCommand;
import io.github.elmergj.movish.api.application.listing.command.TitleAdditionToListOutcome;
import io.github.elmergj.movish.api.application.listing.command.TitleRemovalFromListOutcome;
import io.github.elmergj.movish.api.application.listing.query.ListDetailsQuery;
import io.github.elmergj.movish.api.application.listing.query.ListDetailsView;
import io.github.elmergj.movish.api.application.listing.query.ListItemsDetailsView;
import io.github.elmergj.movish.api.application.listing.query.ListItemsQuery;
import io.github.elmergj.movish.api.application.listing.query.ListingQueryService;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.CustomListFactory;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.Watchlist;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.WatchlistId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.TitleListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListingService {

    private final TitleListRepository titleListRepository;
    private final CustomListFactory customListFactory;
    private final ListingQueryService listingQueryService;

    @Transactional
    public CustomListCreationOutcome createCustomTitleList(CreateCustomTitleListCommand command){

        Watchlist customListCreated = customListFactory.create(
                UserId.from(command.userId()),
                command.name());

        titleListRepository.save(customListCreated);

        return new CustomListCreationOutcome(
                customListCreated.id().value(),
                customListCreated.getName(),
                customListCreated.getDateCreated().toString()
        );
    }

    public ListDetailsView getListDetails(ListDetailsQuery query){

        Watchlist watchlist = titleListRepository.findByIdAndUserOwnerId(
                WatchlistId.from(query.titleListId()), UserId.from(query.userId()))
                .orElseThrow();

        return new ListDetailsView(
                watchlist.id().value(),
                watchlist.getName(),
                watchlist.getTitleIdReferences().size()
        );
    }

    @Transactional
    public CustomListDeletionOutcome deleteCustomTitleList(DeleteCustomListCommand command){

        Watchlist customUserWatchlist = titleListRepository.findByIdAndUserOwnerId(
                WatchlistId.from(command.customListId()), UserId.from(command.userId()))
                .orElseThrow();

        customUserWatchlist.canBeDeleted();

        titleListRepository.delete(customUserWatchlist);

        return new CustomListDeletionOutcome(
                customUserWatchlist.id().value(),
                customUserWatchlist.getName()
        );
    }

    @Transactional
    public TitleAdditionToListOutcome addTitleToList(AddTitleToListCommand command){

        Watchlist customUserWatchlist = titleListRepository.findByIdAndUserOwnerId(
                        WatchlistId.from(command.customListId()), UserId.from(command.userId()))
                .orElseThrow();

        customUserWatchlist.addTitle(TitleId.from(command.titleId()));

        titleListRepository.save(customUserWatchlist);

        return new TitleAdditionToListOutcome(
                customUserWatchlist.id().value(),
                command.titleId(),
                customUserWatchlist.getTitleIdReferences().size()
        );
    }


    public ListItemsDetailsView getListItemsDetails(ListItemsQuery query){
        Watchlist watchlist = titleListRepository.findByIdAndUserOwnerId(
                        WatchlistId.from(query.titleListId()), UserId.from(query.userId()))
                .orElseThrow();

        return listingQueryService.getListItemsDetails(watchlist);
    }

    @Transactional
    public TitleRemovalFromListOutcome removeTitleFromList(RemoveTitleFromListCommand command){

        Watchlist customUserWatchlist = titleListRepository.findByIdAndUserOwnerId(
                        WatchlistId.from(command.customListId()), UserId.from(command.userId()))
                .orElseThrow();


        customUserWatchlist.removeTitle(TitleId.from(command.titleId()));

        titleListRepository.save(customUserWatchlist);

        return new TitleRemovalFromListOutcome(
                customUserWatchlist.id().value(),
                command.titleId(),
                customUserWatchlist.getTitleIdReferences().size()
        );
    }

    @Transactional
    public ListNameUpdateOutcome updateCustomTitleListName(UpdateCustomListNameCommand command){

        Watchlist customUserWatchlist = titleListRepository.findByIdAndUserOwnerId(
                        WatchlistId.from(command.customListId()), UserId.from(command.userId()))
                .orElseThrow();

        customUserWatchlist.updateListName(command.newName());

        titleListRepository.save(customUserWatchlist);

        return new ListNameUpdateOutcome(
                customUserWatchlist.id().value(),
                customUserWatchlist.getName(),
                customUserWatchlist.getTitleIdReferences().size());
    }
}