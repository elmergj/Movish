package io.github.elmergj.movish.api.application.library;

import io.github.elmergj.movish.api.application.Result;
import io.github.elmergj.movish.api.application.Result.FailureOutcome;
import io.github.elmergj.movish.api.application.catalog.MediaCatalogService;
import io.github.elmergj.movish.api.application.library.command.AddTitleToLibraryCommand;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementFailure.TitleAlreadyInLibrary;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleAdditionOutcome;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleFavoriteOutcome;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleRemovalOutcome;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleTrackingUpdateOutcome;
import io.github.elmergj.movish.api.application.library.command.RemoveTitleCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleFavoriteStatusCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleTrackingStatusCommand;
import io.github.elmergj.movish.api.application.library.query.TitleDetails;
import io.github.elmergj.movish.api.application.library.query.TitleDetailsQuery;
import io.github.elmergj.movish.api.application.library.query.TitleDetailsView;
import io.github.elmergj.movish.api.domain.model.entity.library.MediaId;
import io.github.elmergj.movish.api.domain.model.entity.library.MediaType;
import io.github.elmergj.movish.api.domain.model.entity.library.Title;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.library.TrackingStatus;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.TitleRepository;
import io.github.elmergj.movish.api.domain.shared.EntityIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final TitleRepository titleRepository;
    private final MediaCatalogService mediaCatalogService;
    private final EntityIdGenerator entityIdGenerator;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public Result<TitleAdditionOutcome, TitleAlreadyInLibrary> addTitleToLibrary(AddTitleToLibraryCommand command){

        var userId = UserId.from(command.userId());
        var mediaId = MediaId.from(command.mediaId());
        var mediaType = MediaType.fromExternalValue(command.mediaType());

//        Si el media_id existe para el mismo user_id
        if (titleRepository.existsUniqueTitle(userId, mediaId, mediaType)) {
            return Result.failure(new TitleAlreadyInLibrary(mediaId.value())); // bug: to handle null.
        }

        var media = mediaCatalogService.getMediaBasic(mediaId.value(), command.mediaType());

        var title = Title.create(
                entityIdGenerator.generate(TitleId::from),
                MediaId.from(media.mediaId()),
                media.mediaName(),
                MediaType.fromExternalValue(media.mediaType()),
                userId
        );

        titleRepository.save(title);

        return Result.success(new TitleAdditionOutcome(
                title.id().value(),
                title.getMediaId().value(),
                title.getName(),
                title.getDateAdded().toString()
        ));
    }

    public TitleDetailsView getTitleDetails(TitleDetailsQuery query){

        var title = titleRepository.findByIdAndUserId(
                TitleId.from(query.titleId()), UserId.from(query.userId()))
                .orElseThrow();

        var media = mediaCatalogService.getMediaSummary(title.getMediaId().value(), title.getMediaType().externalValue());

        var titleDetails = new TitleDetails(
                title.getMediaId().value(),
                title.getName(),
                media.releaseDate()

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
    public Result<TitleFavoriteOutcome, FailureOutcome> updateTitleFavoriteStatus(UpdateTitleFavoriteStatusCommand command){

        UserId userId = UserId.from(command.userId());

        Title title = titleRepository.findByIdAndUserId(
                        TitleId.from(command.titleId()), userId)
                .orElseThrow();

        title.updateFavoriteStatus(userId, command.favorite());

        titleRepository.save(title);

        title.pullEvents().forEach(publisher::publishEvent);

        return Result.success(new TitleFavoriteOutcome(
                title.getMediaId().value(),
                title.isFavorite()
        ));
    }

    @Transactional
//    public Result<TitleTrackingUpdateOutcome, FailureOutcome> updateTitleTrackingStatus(UpdateTitleTrackingStatusCommand command){
    public Result<TitleTrackingUpdateOutcome, FailureOutcome> updateTitleTrackingStatus(UpdateTitleTrackingStatusCommand command){
        Title title = titleRepository.findByIdAndUserId(
                        TitleId.from(command.titleId()), UserId.from(command.userId()))
                .orElseThrow();


        //Optimize: Verify the format of the incoming value, or change to enums with codes and use @JasonValue in request to
        String formatedValued = String.format(command.trackingStatus()).toUpperCase();
        title.updateTrackingStatus(TrackingStatus.valueOf(formatedValued));

        titleRepository.save(title);

        return Result.success(new TitleTrackingUpdateOutcome(
                title.getMediaId().value(),
                title.getTrackingStatus().name(),
                title.getDateAdded().toString(),
                title.isFavorite()
        ));
    }

    @Transactional
    public Result<TitleRemovalOutcome, FailureOutcome> deleteTitle(RemoveTitleCommand command){

        UserId userId = UserId.from(command.titleId());

        Title title = titleRepository.findByIdAndUserId(
                        TitleId.from(command.titleId()), UserId.from(command.userId()))
                .orElseThrow();

        title.remove(userId);

        var outcome = new TitleRemovalOutcome(title.id().value());

        titleRepository.delete(title);

        title.pullEvents().forEach(publisher::publishEvent);

//        return outcome;
        return null;
    }
}
