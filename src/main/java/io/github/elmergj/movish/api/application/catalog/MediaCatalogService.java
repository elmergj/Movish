package io.github.elmergj.movish.api.application.catalog;

import io.github.elmergj.movish.api.application.catalog.command.SearchMediaCommand;
import io.github.elmergj.movish.api.domain.model.entity.catalog.MediaCatalogSource;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaAverageRating;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaProvider;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.MediaSummaryResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchResultSet;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.MediaDetailsResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaId;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaType;
import io.github.elmergj.movish.api.domain.model.entity.catalog.media.Media;
import io.github.elmergj.movish.api.domain.repository.MediaRepository;
import io.github.elmergj.movish.api.domain.shared.EntityIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaCatalogService {

    private final MediaCatalogSource mediaCatalogSource;
    private final MediaRepository mediaRepository;
    private final EntityIdGenerator entityIdGenerator;

    public SearchResultSet<MediaSummaryResult> searchMediaByQuery(SearchMediaCommand command) {

        return mediaCatalogSource.searchMediaByQuery(command.query(), command.page(),
               command.pageSize());

    }

    private MediaDetailsResult fetchMediaDetails(String externalMediaId, MediaType mediaType) {
        return mediaCatalogSource.fetchMediaDetails(externalMediaId, mediaType);
    }


    public Media getMedia(String externalMediaId, MediaType mediaType) {

        return mediaRepository.findByExternalIdAndMediaType(externalMediaId, mediaType)
                .orElseGet(() -> {
                    MediaDetailsResult mediaDetails = fetchMediaDetails(externalMediaId, mediaType);
                    Media media = new Media(
                            entityIdGenerator.generate(MediaId::from),
                            List.of(mediaDetails.externalMediaId()),
                            mediaDetails.name(),
                            mediaDetails.mediaType(),
                            List.of(MediaAverageRating.of(10.0, new MediaProvider("Provider"), 10)),
                            null, // Bug: to solve!
                            mediaDetails.releaseDate()
                            );
                    saveMediaToCatalog(media);
                    return media;
                });
    }

    private void saveMediaToCatalog(Media media) {
        mediaRepository.save(media);
    }
}