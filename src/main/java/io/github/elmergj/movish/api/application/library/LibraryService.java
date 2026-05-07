package io.github.elmergj.movish.api.application.library;

import io.github.elmergj.movish.api.application.catalog.TitleCatalogService;
import io.github.elmergj.movish.api.application.library.command.DeleteUserTitleCommand;
import io.github.elmergj.movish.api.application.library.command.SaveTitleToLibraryCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleFavoriteStatusCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleTrackingStatusCommand;
import io.github.elmergj.movish.api.application.library.command.UserTitleCreationOutcome;
import io.github.elmergj.movish.api.application.library.command.UserTitleDeletionOutcome;
import io.github.elmergj.movish.api.application.library.command.UserTitleFavoriteOutcome;
import io.github.elmergj.movish.api.application.library.command.UserTitleTrackingUpdateOutcome;
import io.github.elmergj.movish.api.application.library.query.TitleDetails;
import io.github.elmergj.movish.api.application.library.query.UserTitleDetailsQuery;
import io.github.elmergj.movish.api.application.library.query.UserTitleDetailsView;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.MediaType;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.Title;
import io.github.elmergj.movish.api.domain.model.entity.library.TrackingStatus;
import io.github.elmergj.movish.api.domain.model.entity.library.UserTitle;
import io.github.elmergj.movish.api.domain.model.entity.library.UserTitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.TitleRepository;
import io.github.elmergj.movish.api.domain.repository.UserTitleRepository;
import io.github.elmergj.movish.api.domain.shared.EntityIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final UserTitleRepository userTitleRepository;
    private final TitleRepository titleRepository;
    private final TitleCatalogService titleCatalogService;
    private final EntityIdGenerator entityIdGenerator;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public UserTitleCreationOutcome createUserTitle(SaveTitleToLibraryCommand command){

        UserId userId = UserId.from(command.userId());

        Title title = titleCatalogService.getTitleDetails(command.externalTitleId(), MediaType.valueOf(command.mediaType()));

        UserTitle userTitle = UserTitle.create(
                entityIdGenerator.generate(UserTitleId::from),
                title.id(),
                userId
        );

        userTitleRepository.save(userTitle);

        return new UserTitleCreationOutcome(
                userTitle.id().value(),
                title.name(),
                //Ignore title.imdbId(),
                title.id().value(),
                //Ignore title.imdbTitleRating().value(),
                title.tmdbTitleRating().value(),
                userTitle.getTrackingStatus().name(),
                userTitle.getDateAdded().toString()
        );
    }

    public UserTitleDetailsView getUserTitleDetails(UserTitleDetailsQuery query){

        UserTitle userTitle = userTitleRepository.findByIdAndUserOwnerId(
                UserTitleId.from(query.userTitleId()), UserId.from(query.userId()))
                .orElseThrow();

        Title title = titleRepository.findById(userTitle.getTitleId()).orElseThrow();

        TitleDetails titleDetails = new TitleDetails(
                title.id().value(),
                title.name(),
                title.releaseDate().toString()
        );

        return new UserTitleDetailsView(
                userTitle.id().value(),
                userTitle.getTrackingStatus().name(),
                userTitle.getDateAdded().toString(),
                userTitle.isFavorite(),
                titleDetails
        );
    }

    @Transactional
    public UserTitleFavoriteOutcome updateUserTitleFavoriteStatus(UpdateTitleFavoriteStatusCommand command){

        UserId userId = UserId.from(command.userId());

        UserTitle userTitle = userTitleRepository.findByIdAndUserOwnerId(
                        UserTitleId.from(command.userTitleId()), userId)
                .orElseThrow();

        userTitle.updateFavoriteStatus(userId, command.favorite());

        userTitleRepository.save(userTitle);

        userTitle.pullEvents().forEach(publisher::publishEvent);

        return new UserTitleFavoriteOutcome(
                userTitle.getTitleId().value(),
                userTitle.isFavorite()
        );
    }

    @Transactional
    public UserTitleTrackingUpdateOutcome updateUserTitleTrackingStatus(UpdateTitleTrackingStatusCommand command){

        UserTitle userTitle = userTitleRepository.findByIdAndUserOwnerId(
                        UserTitleId.from(command.userTitleId()), UserId.from(command.userId()))
                .orElseThrow();


        //Optimize: Verify the format of the incoming value, or change to enums with codes and use @JasonValue in request to
        String formatedValued = String.format(command.trackingStatus()).toUpperCase();
        userTitle.updateTrackingStatus(TrackingStatus.valueOf(formatedValued));

        userTitleRepository.save(userTitle);

        return new UserTitleTrackingUpdateOutcome(
                userTitle.getTitleId().value(),
                userTitle.getTrackingStatus().name(),
                userTitle.getDateAdded().toString(),
                userTitle.isFavorite()
        );
    }

    @Transactional
    public UserTitleDeletionOutcome deleteUserTitle(DeleteUserTitleCommand command){

        UserId userId = UserId.from(command.userTitleId());

        UserTitle userTitle = userTitleRepository.findByIdAndUserOwnerId(
                        UserTitleId.from(command.userTitleId()), UserId.from(command.userId()))
                .orElseThrow();

        userTitle.delete(userId);

        var outcome = new UserTitleDeletionOutcome(userTitle.id().value());

        userTitleRepository.delete(userTitle);

        userTitle.pullEvents().forEach(publisher::publishEvent);

        return outcome;
    }
}
