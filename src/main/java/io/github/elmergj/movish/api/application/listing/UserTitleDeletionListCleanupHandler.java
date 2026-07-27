package io.github.elmergj.movish.api.application.listing;

import io.github.elmergj.movish.api.domain.model.entity.library.TitleUnlinkedEvent;
import io.github.elmergj.movish.api.domain.repository.TitleListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserTitleDeletionListCleanupHandler {

    private final TitleListRepository repository;

    @EventListener
    @Transactional
    public void handle(TitleUnlinkedEvent event){
        repository.removeReferenceFromAllLists(event.titleId());
    }
}
