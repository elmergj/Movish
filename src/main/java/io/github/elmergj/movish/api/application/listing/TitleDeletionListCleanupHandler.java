package io.github.elmergj.movish.api.application.listing;

import io.github.elmergj.movish.api.domain.model.entity.library.TitleUnlinkedEvent;
import io.github.elmergj.movish.api.domain.repository.WatchlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TitleDeletionListCleanupHandler {

    private final WatchlistRepository repository;

    @EventListener
    @Transactional
    public void handle(TitleUnlinkedEvent event){
        repository.removeReferenceFromAllLists(event.titleId());
    }
}
