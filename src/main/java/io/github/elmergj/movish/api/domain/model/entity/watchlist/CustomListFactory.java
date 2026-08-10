package io.github.elmergj.movish.api.domain.model.entity.watchlist;

import io.github.elmergj.movish.api.domain.exception.DomainRuleViolationException;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.WatchlistRepository;
import io.github.elmergj.movish.api.domain.shared.EntityIdGenerator;
import org.springframework.stereotype.Component;


@Component
public class CustomListFactory{

    private final WatchlistRepository watchlistRepository;
    private final EntityIdGenerator entityIdGenerator;

    public CustomListFactory(WatchlistRepository watchlistRepository, EntityIdGenerator entityIdGenerator) {
        this.watchlistRepository = watchlistRepository;
        this.entityIdGenerator = entityIdGenerator;
    }

    public Watchlist create(UserId userId, String name) {

        if (watchlistRepository.existByUserOwnerIdAndListName(userId, name)){
            throw new DomainRuleViolationException("The list name " + name + " is already in use");
        }

        return Watchlist.create(
                entityIdGenerator.generate(WatchlistId::from),
                userId,
                name,
                WatchlistType.CUSTOM_USER_WATCHLIST);
    }

}
