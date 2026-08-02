package io.github.elmergj.movish.api.application.catalog;

import io.github.elmergj.movish.api.application.catalog.search.MediaBasicResult;
import io.github.elmergj.movish.api.application.catalog.search.MediaDetailsResult;
import io.github.elmergj.movish.api.application.catalog.search.MediaSummaryResult;
import io.github.elmergj.movish.api.application.catalog.search.SearchMediaCommand;
import io.github.elmergj.movish.api.application.catalog.search.SearchResultSet;
import io.github.elmergj.movish.api.domain.model.entity.catalog.MediaCatalogSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaCatalogService {

    private final MediaCatalogSource mediaCatalogSource;
//    private final MediaRepository mediaRepository;
//    private final EntityIdGenerator entityIdGenerator;
//    private final MediaProviderPriorityPolicy providerPriorityPolicy;

    public SearchResultSet<MediaSummaryResult> searchMediaByQuery(SearchMediaCommand command) {

        return mediaCatalogSource.searchMediaByQuery(command.query(), command.page(),
               command.pageSize());

    }

    public MediaSummaryResult getMediaSummary(String mediaId, String mediaType) {
        return mediaCatalogSource.fetchMediaSummary(mediaId, mediaType);
    }


    public MediaBasicResult  getMediaBasic(String mediaId, String mediaType) {
        return mediaCatalogSource.fetchMediaBasicData(mediaId, mediaType);
    }

//    public Media getMedia(String externalMediaId, MediaType mediaType) {
//
//        return mediaRepository.findByExternalIdAndMediaType(externalMediaId, mediaType)
//                .orElseGet(() -> {
//                    MediaDetailsResult mediaDetails = fetchMediaDetails(externalMediaId, mediaType);
//                    Media media = new Media(
//                            entityIdGenerator.generate(MediaId::from),
//                            List.of(mediaDetails.externalMediaId()),
//                            mediaDetails.name(),
//                            mediaDetails.mediaType(),
//                            null,
//                            null, // Bug: to solve!
//                            mediaDetails.releaseDate()
//                            );
//                    saveMediaToCatalog(media);
//                    return media;
//                });
//    }


    // Internal Methods
//    private void saveMediaToCatalog(Media media) {
//        mediaRepository.save(media);
//    }

    private MediaDetailsResult fetchMediaDetails(String externalMediaId, String mediaType) {
        return mediaCatalogSource.fetchMediaDetails(externalMediaId, mediaType);
    }

//    // Raw method
//    private MediaExternalId selectMediaProvider(Collection<MediaExternalId> externalIds){
//        return providerPriorityPolicy.select(externalIds);
//    }
}