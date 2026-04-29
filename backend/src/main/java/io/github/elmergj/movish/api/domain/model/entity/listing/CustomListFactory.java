package io.github.elmergj.movish.api.domain.model.entity.listing;

import io.github.elmergj.movish.api.domain.exception.DomainRuleViolationException;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.TitleListRepository;
import io.github.elmergj.movish.api.domain.shared.EntityIdGenerator;
import org.springframework.stereotype.Component;


@Component
public class CustomListFactory{

    private final TitleListRepository titleListRepository;
    private final EntityIdGenerator entityIdGenerator;

    public CustomListFactory(TitleListRepository titleListRepository, EntityIdGenerator entityIdGenerator) {
        this.titleListRepository = titleListRepository;
        this.entityIdGenerator = entityIdGenerator;
    }

    public TitleList create(UserId userId, String name) {

        if (titleListRepository.existByUserOwnerIdAndListName(userId, name)){
            throw new DomainRuleViolationException("The list name " + name + " is already in use");
        }

        return TitleList.create(
                entityIdGenerator.generate(TitleListId::from),
                userId,
                name,
                TitleListType.USER_CUSTOM_LIST);
    }

}
