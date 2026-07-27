package io.github.elmergj.movish.api.application.listing;

import io.github.elmergj.movish.api.domain.model.entity.library.TitleMarkedAsFavoriteEvent;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleUnmarkedAsFavoriteEvent;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleListType;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleList;
import io.github.elmergj.movish.api.domain.repository.TitleListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FavoriteListTitleAdditionHandler {

    private final TitleListRepository repository;

    @EventListener
    @Transactional
    public void handle(TitleMarkedAsFavoriteEvent event){

        TitleList favoriteList = repository.findByUserOwnerIdAndListType(event.userId(), TitleListType.FAVORITE_LIST).orElseThrow();

        favoriteList.applyAddTitleToDefaultList(event.titleId());

        repository.save(favoriteList);
    }

    @EventListener
    @Transactional
    public void handle(TitleUnmarkedAsFavoriteEvent event){
        TitleList favoriteList = repository.findByUserOwnerIdAndListType(event.userId(), TitleListType.FAVORITE_LIST).orElseThrow();

        favoriteList.applyRemoveTitleFromDefaultList(event.titleId());

        repository.save(favoriteList);
    }

}
