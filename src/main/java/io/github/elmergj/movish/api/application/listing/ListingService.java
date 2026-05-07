package io.github.elmergj.movish.api.application.listing;

import io.github.elmergj.movish.api.application.listing.command.AddTitleToListCommand;
import io.github.elmergj.movish.api.application.listing.command.UpdateCustomListNameCommand;
import io.github.elmergj.movish.api.application.listing.command.CreateCustomTitleListCommand;
import io.github.elmergj.movish.api.application.listing.command.CustomListCreationOutcome;
import io.github.elmergj.movish.api.application.listing.command.CustomListDeletionOutcome;
import io.github.elmergj.movish.api.application.listing.command.DeleteCustomListCommand;
import io.github.elmergj.movish.api.application.listing.command.ListNameUpdateOutcome;
import io.github.elmergj.movish.api.application.listing.command.RemoveTitleFromListCommand;
import io.github.elmergj.movish.api.application.listing.command.UserTitleAdditionToListOutcome;
import io.github.elmergj.movish.api.application.listing.command.UserTitleRemovalFromListOutcome;
import io.github.elmergj.movish.api.application.listing.query.ListDetailsQuery;
import io.github.elmergj.movish.api.application.listing.query.ListDetailsView;
import io.github.elmergj.movish.api.application.listing.query.ListItemsDetailsView;
import io.github.elmergj.movish.api.application.listing.query.ListItemsQuery;
import io.github.elmergj.movish.api.application.listing.query.ListingQueryService;
import io.github.elmergj.movish.api.domain.model.entity.library.UserTitleId;
import io.github.elmergj.movish.api.domain.model.entity.listing.CustomListFactory;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleList;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleListId;
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

        TitleList customListCreated = customListFactory.create(
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

        TitleList titleList = titleListRepository.findByIdAndUserOwnerId(
                TitleListId.from(query.titleListId()), UserId.from(query.userId()))
                .orElseThrow();

        return new ListDetailsView(
                titleList.id().value(),
                titleList.getName(),
                titleList.getUserTitleIdReferences().size()
        );
    }

    @Transactional
    public CustomListDeletionOutcome deleteCustomTitleList(DeleteCustomListCommand command){

        TitleList customUserTitleList = titleListRepository.findByIdAndUserOwnerId(
                TitleListId.from(command.customListId()), UserId.from(command.userId()))
                .orElseThrow();

        customUserTitleList.canBeDeleted();

        titleListRepository.delete(customUserTitleList);

        return new CustomListDeletionOutcome(
                customUserTitleList.id().value(),
                customUserTitleList.getName()
        );
    }

    @Transactional
    public UserTitleAdditionToListOutcome addTitleToList(AddTitleToListCommand command){

        TitleList customUserTitleList = titleListRepository.findByIdAndUserOwnerId(
                        TitleListId.from(command.customListId()), UserId.from(command.userId()))
                .orElseThrow();

        customUserTitleList.addUserTitle(UserTitleId.from(command.titleId()));

        titleListRepository.save(customUserTitleList);

        return new UserTitleAdditionToListOutcome(
                customUserTitleList.id().value(),
                command.titleId(),
                customUserTitleList.getUserTitleIdReferences().size()
        );
    }


    public ListItemsDetailsView getListItemsDetails(ListItemsQuery query){
        TitleList titleList = titleListRepository.findByIdAndUserOwnerId(
                        TitleListId.from(query.titleListId()), UserId.from(query.userId()))
                .orElseThrow();

        return listingQueryService.getListItemsDetails(titleList);
    }

    @Transactional
    public UserTitleRemovalFromListOutcome removeTitleFromList(RemoveTitleFromListCommand command){

        TitleList customUserTitleList = titleListRepository.findByIdAndUserOwnerId(
                        TitleListId.from(command.customListId()), UserId.from(command.userId()))
                .orElseThrow();


        customUserTitleList.removeUserTitle(UserTitleId.from(command.titleId()));

        titleListRepository.save(customUserTitleList);

        return new UserTitleRemovalFromListOutcome(
                customUserTitleList.id().value(),
                command.titleId(),
                customUserTitleList.getUserTitleIdReferences().size()
        );
    }

    @Transactional
    public ListNameUpdateOutcome updateCustomTitleListName(UpdateCustomListNameCommand command){

        TitleList customUserTitleList = titleListRepository.findByIdAndUserOwnerId(
                        TitleListId.from(command.customListId()), UserId.from(command.userId()))
                .orElseThrow();

        customUserTitleList.updateListName(command.newName());

        titleListRepository.save(customUserTitleList);

        return new ListNameUpdateOutcome(
                customUserTitleList.id().value(),
                customUserTitleList.getName(),
                customUserTitleList.getUserTitleIdReferences().size());
    }
}