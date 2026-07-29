package io.github.elmergj.movish.api.application.library;

import io.github.elmergj.movish.api.application.Result;
import io.github.elmergj.movish.api.application.Result.FailureOutcome;
import io.github.elmergj.movish.api.application.catalog.MediaCatalogService;
import io.github.elmergj.movish.api.application.catalog.MediaProviderRegistry;
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
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.Media;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaExternalId;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaType;
import io.github.elmergj.movish.api.domain.model.entity.library.Title;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.library.TrackingStatus;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.MediaRepository;
import io.github.elmergj.movish.api.domain.repository.TitleRepository;
import io.github.elmergj.movish.api.domain.shared.EntityIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final TitleRepository titleRepository;
    private final MediaRepository mediaRepository;
    private final MediaCatalogService mediaCatalogService;
    private final EntityIdGenerator entityIdGenerator;
    private final ApplicationEventPublisher publisher;
    private final TitleInternalIdentityResolver titleIdentityResolver;
    private final MediaProviderRegistry mediaProviderRegistry;

    @Transactional
    public Result<TitleAdditionOutcome, TitleAlreadyInLibrary> addTitleToLibrary(AddTitleToLibraryCommand command){

        //Mapeo de media_external_ids externos a internos (con Id)
        Collection<MediaExternalId> externalIds =
                command.mediaExternalReferences().stream()
                        .map(ref -> new MediaExternalId(
                                mediaProviderRegistry.findByExternalName(ref.mediaProvider()),
                                ref.value()))
                        .toList();

        //Resolver media_id interno mediante los external_ids
        var mediaId = titleIdentityResolver.resolveByExternalIds(externalIds);
        var userId = UserId.from(command.userId());

        //Si el media_id existe para el mismo user_id
        if (titleRepository.existsByMediaIdAndUserId(mediaId, userId)){
            return Result.failure(new TitleAlreadyInLibrary(null));
        }

        var media = mediaCatalogService.getMedia(
                command.mediaId(),
                MediaType.valueOf(command.mediaType()));

        var title = Title.create(
                entityIdGenerator.generate(TitleId::from),
                media.id(),
                userId
        );

        titleRepository.save(title);

        return Result.success(new TitleAdditionOutcome(
                title.id().value(),
                media.externalIds().stream()
                        .map(),
                media.name(),
                title.getDateAdded().toString()
        ));
    }

    public TitleDetailsView getTitleDetails(TitleDetailsQuery query){

        Title title = titleRepository.findByIdAndUserId(
                TitleId.from(query.titleId()), UserId.from(query.userId()))
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
