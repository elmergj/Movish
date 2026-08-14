package io.github.elmergj.movish.api.application.listing;

import io.github.elmergj.movish.api.domain.model.entity.library.TitleMarkedAsFavoriteEvent;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleUnmarkedAsFavoriteEvent;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.Watchlist;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.WatchlistType;
import io.github.elmergj.movish.api.domain.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FavoriteListTitleAdditionHandler {

    private final WatchlistRepository repository;

    //Note: Evaluate removing @Transactional here.
    /*It is currently redundant as this listener runs synchronously within the Use Case's active transaction.
    Future refinement: Explore replacing this with a custom @RequiresExistingTransaction
    marker annotation and enforcing the rule via ArchTest.*/
    @EventListener
    @Transactional
    public void handle(TitleMarkedAsFavoriteEvent event){

        Watchlist favoriteList = repository.findByUserOwnerIdAndListType(event.userId(), WatchlistType.FAVORITE_LIST).orElseThrow();

        favoriteList.applyAddTitleToDefaultList(event.titleId());

        repository.save(favoriteList);
    }

    @EventListener
    @Transactional
    public void handle(TitleUnmarkedAsFavoriteEvent event){
        Watchlist favoriteList = repository.findByUserOwnerIdAndListType(event.userId(), WatchlistType.FAVORITE_LIST).orElseThrow();

        favoriteList.applyRemoveTitleFromDefaultList(event.titleId());

        repository.save(favoriteList);
    }

}
