package io.github.elmergj.movish.api.application.library;

import io.github.elmergj.movish.api.application.Result;
import io.github.elmergj.movish.api.application.catalog.MediaCatalogService;
import io.github.elmergj.movish.api.application.library.command.AddTitleToLibraryCommand;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementFailure.TitleAlreadyInLibrary;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementFailure.TitleFavoriteStatusAlreadyUpdated;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementFailure.TitleTrackingStatusAlreadyUpdated;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleAdditionOutcome;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleFavoriteOutcome;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleRemovalOutcome;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleTrackingUpdateOutcome;
import io.github.elmergj.movish.api.application.library.command.RemoveTitleCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleFavoriteStatusCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleTrackingStatusCommand;
import io.github.elmergj.movish.api.application.library.query.TitleQuery.TitleDetailsQuery;
import io.github.elmergj.movish.api.application.library.query.TitleView.TitleDetailsView;
import io.github.elmergj.movish.api.application.library.query.TitleView.TitleDetailsView.TitleDetails;
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

        if (titleRepository.existsUniqueTitle(userId, mediaId, mediaType)) {
            return Result.failure(new TitleAlreadyInLibrary(mediaId.value())); // todo: set the failure outcome format.
        }

        var media = mediaCatalogService.getMediaOverview(mediaId.value(), command.mediaType());

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

        var media = mediaCatalogService.getMediaDetails(title.getMediaId().value(), title.getMediaType().externalValue());

        var titleDetails = new TitleDetails(
                media.mediaId(),
                media.name(),
                media.releaseDate()

        );

        return new TitleDetailsView(
                title.id().value(),
                title.getTrackingStatus().name().toLowerCase(),
                title.getDateAdded().toString(),
                title.isFavorite(),
                titleDetails
        );
    }

    @Transactional
    public Result<TitleFavoriteOutcome, TitleFavoriteStatusAlreadyUpdated> updateTitleFavoriteStatus(UpdateTitleFavoriteStatusCommand command){

        var userId = UserId.from(command.userId());
        var titleId = TitleId.from(command.titleId());

        var title = titleRepository.findByIdAndUserId(titleId, userId)
                .orElseThrow();

        if (!title.canUpdateFavoriteStatus(userId, command.favorite())){
            return Result.failure(new TitleFavoriteStatusAlreadyUpdated(titleId.value()));
        }

        titleRepository.save(title);
        title.pullEvents().forEach(publisher::publishEvent);

        return Result.success(new TitleFavoriteOutcome(
                title.id().value(),
                title.isFavorite()
        ));
    }

    @Transactional
    public Result<TitleTrackingUpdateOutcome, TitleTrackingStatusAlreadyUpdated> updateTitleTrackingStatus(UpdateTitleTrackingStatusCommand command){

        var title = titleRepository.findByIdAndUserId(
                        TitleId.from(command.titleId()), UserId.from(command.userId()))
                .orElseThrow();

        var trackingStatus = TrackingStatus.fromExternalValue(command.trackingStatus());

        //Optimize: Maybe can be replaced by "enums with codes and use @JasonValue..."

        if (!title.canUpdateTrackingStatus(trackingStatus)){
            return Result.failure(new TitleTrackingStatusAlreadyUpdated(title.id().value()));
        }

        titleRepository.save(title);

        return Result.success(new TitleTrackingUpdateOutcome(
                title.id().value(),
                title.getTrackingStatus().name(),
                title.getDateAdded().toString(),
                title.isFavorite()
        ));
    }

    // No failure command?
    @Transactional
    public TitleRemovalOutcome deleteTitle(RemoveTitleCommand command){

        var userId = UserId.from(command.titleId());

        var title = titleRepository.findByIdAndUserId(
                        TitleId.from(command.titleId()), UserId.from(command.userId()))
                .orElseThrow();

        title.remove(userId);

        var outcome = new TitleRemovalOutcome(title.id().value());

        titleRepository.delete(title);

        title.pullEvents().forEach(publisher::publishEvent);

        return outcome;
    }
}
