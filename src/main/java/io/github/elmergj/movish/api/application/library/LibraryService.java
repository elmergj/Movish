package io.github.elmergj.movish.api.application.library;

import io.github.elmergj.movish.api.application.Result;
import io.github.elmergj.movish.api.application.Result.FailureReason;
import io.github.elmergj.movish.api.application.catalog.MediaCatalogService;
import io.github.elmergj.movish.api.application.library.LibraryManagementOutcome.UserTitleCreationOutcome;
import io.github.elmergj.movish.api.application.library.LibraryManagementOutcome.UserTitleDeletionOutcome;
import io.github.elmergj.movish.api.application.library.LibraryManagementOutcome.UserTitleFavoriteOutcome;
import io.github.elmergj.movish.api.application.library.LibraryManagementOutcome.UserTitleTrackingUpdateOutcome;
import io.github.elmergj.movish.api.application.library.command.DeleteUserTitleCommand;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementFailure.UserTitleAlreadyInLibrary;
import io.github.elmergj.movish.api.application.library.command.SaveTitleToLibraryCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleFavoriteStatusCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleTrackingStatusCommand;
import io.github.elmergj.movish.api.application.library.query.TitleDetails;
import io.github.elmergj.movish.api.application.library.query.UserTitleDetailsQuery;
import io.github.elmergj.movish.api.application.library.query.UserTitleDetailsView;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.Media;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaType;
import io.github.elmergj.movish.api.domain.model.entity.library.Title;
import io.github.elmergj.movish.api.domain.model.entity.library.TrackingStatus;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.MediaRepository;
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
    private final MediaRepository mediaRepository;
    private final MediaCatalogService mediaCatalogService;
    private final EntityIdGenerator entityIdGenerator;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public Result<UserTitleCreationOutcome, UserTitleAlreadyInLibrary> addMediaToLibrary(SaveTitleToLibraryCommand command){

        UserId userId = UserId.from(command.userId());

        Media media = mediaCatalogService.getMedia(command.externalTitleId(), MediaType.valueOf(command.mediaType()));

        Title title = Title.create(
                entityIdGenerator.generate(TitleId::from),
                media.id(),
                userId
        );

        userTitleRepository.save(title);

        if (true){
            return Result.success(new UserTitleCreationOutcome(
                    title.id().value(),
                    media.name(),
                    media.id().value(),
                    10.0, // Bug: to solve!
                    title.getTrackingStatus().name(),
                    title.getDateAdded().toString()
            ));
        }

        return Result.failure(new UserTitleAlreadyInLibrary(title.id().value()));
    }

    public UserTitleDetailsView getUserTitleDetails(UserTitleDetailsQuery query){

        Title title = userTitleRepository.findByIdAndUserOwnerId(
                TitleId.from(query.userTitleId()), UserId.from(query.userId()))
                .orElseThrow();

        Media media = mediaRepository.findById(title.getMediaId()).orElseThrow();

        TitleDetails titleDetails = new TitleDetails(
                media.id().value(),
                media.name(),
                media.releaseDate().toString()
        );

        return new UserTitleDetailsView(
                title.id().value(),
                title.getTrackingStatus().name(),
                title.getDateAdded().toString(),
                title.isFavorite(),
                titleDetails
        );
    }

    @Transactional
    public Result<UserTitleFavoriteOutcome, FailureReason> updateUserTitleFavoriteStatus(UpdateTitleFavoriteStatusCommand command){

        UserId userId = UserId.from(command.userId());

        Title title = userTitleRepository.findByIdAndUserOwnerId(
                        TitleId.from(command.userTitleId()), userId)
                .orElseThrow();

        title.updateFavoriteStatus(userId, command.favorite());

        userTitleRepository.save(title);

        title.pullEvents().forEach(publisher::publishEvent);

        return Result.success(new UserTitleFavoriteOutcome(
                title.getMediaId().value(),
                title.isFavorite()
        ));
    }

    @Transactional
//    public Result<UserTitleTrackingUpdateOutcome, FailureReason> updateUserTitleTrackingStatus(UpdateTitleTrackingStatusCommand command){
    public Result<UserTitleTrackingUpdateOutcome, FailureReason> updateUserTitleTrackingStatus(UpdateTitleTrackingStatusCommand command){
        Title title = userTitleRepository.findByIdAndUserOwnerId(
                        TitleId.from(command.userTitleId()), UserId.from(command.userId()))
                .orElseThrow();


        //Optimize: Verify the format of the incoming value, or change to enums with codes and use @JasonValue in request to
        String formatedValued = String.format(command.trackingStatus()).toUpperCase();
        title.updateTrackingStatus(TrackingStatus.valueOf(formatedValued));

        userTitleRepository.save(title);

        return Result.success(new LibraryManagementOutcome.UserTitleTrackingUpdateOutcome(
                title.getMediaId().value(),
                title.getTrackingStatus().name(),
                title.getDateAdded().toString(),
                title.isFavorite()
        ));
    }

    @Transactional
    public Result<UserTitleDeletionOutcome, FailureReason> deleteUserTitle(DeleteUserTitleCommand command){

        UserId userId = UserId.from(command.userTitleId());

        Title title = userTitleRepository.findByIdAndUserOwnerId(
                        TitleId.from(command.userTitleId()), UserId.from(command.userId()))
                .orElseThrow();

        title.remove(userId);

        var outcome = new LibraryManagementOutcome.UserTitleDeletionOutcome(title.id().value());

        userTitleRepository.delete(title);

        title.pullEvents().forEach(publisher::publishEvent);

//        return outcome;
        return null;
    }
}
