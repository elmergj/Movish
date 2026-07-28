package io.github.elmergj.movish.api.application.library;

import io.github.elmergj.movish.api.application.Result;
import io.github.elmergj.movish.api.application.Result.FailureOutcome;
import io.github.elmergj.movish.api.application.catalog.MediaCatalogService;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleAdditionOutcome;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleRemovalOutcome;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleFavoriteOutcome;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleTrackingUpdateOutcome;
import io.github.elmergj.movish.api.application.library.command.RemoveTitleCommand;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementFailure.TitleAlreadyInLibrary;
import io.github.elmergj.movish.api.application.library.command.AddTitleToLibraryCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleFavoriteStatusCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleTrackingStatusCommand;
import io.github.elmergj.movish.api.application.library.query.TitleDetails;
import io.github.elmergj.movish.api.application.library.query.TitleDetailsQuery;
import io.github.elmergj.movish.api.application.library.query.TitleDetailsView;
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
    public Result<TitleAdditionOutcome, TitleAlreadyInLibrary> addMediaToLibrary(AddTitleToLibraryCommand command){

        var userId = UserId.from(command.userId());
        var media = mediaCatalogService.getMedia(
                command.mediaId(),
                MediaType.valueOf(command.mediaType()));

        Title title = Title.create(
                entityIdGenerator.generate(TitleId::from),
                media.id(),
                userId
        );
        // PRIMERO COMPROBAR SI EXISTE EL TITULO EN LA BIBLIOTECA
        userTitleRepository.save(title);

        if (true){
            return Result.success(new TitleAdditionOutcome(
                    title.id().value(),
                    media.name(),
                    media.id().value(),
                    10.0, // Bug: to solve!
                    title.getTrackingStatus().name(),
                    title.getDateAdded().toString()
            ));
        }

        return Result.failure(new TitleAlreadyInLibrary(title.id().value()));
    }

    public TitleDetailsView getUserTitleDetails(TitleDetailsQuery query){

        Title title = userTitleRepository.findByIdAndUserOwnerId(
                TitleId.from(query.userTitleId()), UserId.from(query.userId()))
                .orElseThrow();

        Media media = mediaRepository.findById(title.getMediaId()).orElseThrow();

        TitleDetails titleDetails = new TitleDetails(
                media.id().value(),
                media.name(),
                media.releaseDate().toString()
        );

        return new TitleDetailsView(
                title.id().value(),
                title.getTrackingStatus().name(),
                title.getDateAdded().toString(),
                title.isFavorite(),
                titleDetails
        );
    }

    @Transactional
    public Result<TitleFavoriteOutcome, FailureOutcome> updateUserTitleFavoriteStatus(UpdateTitleFavoriteStatusCommand command){

        UserId userId = UserId.from(command.userId());

        Title title = userTitleRepository.findByIdAndUserOwnerId(
                        TitleId.from(command.userTitleId()), userId)
                .orElseThrow();

        title.updateFavoriteStatus(userId, command.favorite());

        userTitleRepository.save(title);

        title.pullEvents().forEach(publisher::publishEvent);

        return Result.success(new TitleFavoriteOutcome(
                title.getMediaId().value(),
                title.isFavorite()
        ));
    }

    @Transactional
//    public Result<TitleTrackingUpdateOutcome, FailureOutcome> updateUserTitleTrackingStatus(UpdateTitleTrackingStatusCommand command){
    public Result<TitleTrackingUpdateOutcome, FailureOutcome> updateUserTitleTrackingStatus(UpdateTitleTrackingStatusCommand command){
        Title title = userTitleRepository.findByIdAndUserOwnerId(
                        TitleId.from(command.userTitleId()), UserId.from(command.userId()))
                .orElseThrow();


        //Optimize: Verify the format of the incoming value, or change to enums with codes and use @JasonValue in request to
        String formatedValued = String.format(command.trackingStatus()).toUpperCase();
        title.updateTrackingStatus(TrackingStatus.valueOf(formatedValued));

        userTitleRepository.save(title);

        return Result.success(new TitleTrackingUpdateOutcome(
                title.getMediaId().value(),
                title.getTrackingStatus().name(),
                title.getDateAdded().toString(),
                title.isFavorite()
        ));
    }

    @Transactional
    public Result<TitleRemovalOutcome, FailureOutcome> deleteUserTitle(RemoveTitleCommand command){

        UserId userId = UserId.from(command.titleId());

        Title title = userTitleRepository.findByIdAndUserOwnerId(
                        TitleId.from(command.titleId()), UserId.from(command.userId()))
                .orElseThrow();

        title.remove(userId);

        var outcome = new TitleRemovalOutcome(title.id().value());

        userTitleRepository.delete(title);

        title.pullEvents().forEach(publisher::publishEvent);

//        return outcome;
        return null;
    }
}
