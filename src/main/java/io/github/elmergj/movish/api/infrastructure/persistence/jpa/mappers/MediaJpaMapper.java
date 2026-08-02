package io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers;

import org.springframework.stereotype.Component;

@Component
public class MediaJpaMapper {

//    public @NonNull MediaEntity toJpaMedia(Media media) {
//        MediaEntity mediaEntity = new MediaEntity();
//
//        mediaEntity.setId(media.id().value());
////        mediaEntity.setExternalMediaId(media.externalIds().get()); //Bug: to solve!
//        mediaEntity.setName(media.name());
//        mediaEntity.setReleaseDate(media.releaseDate());
////        mediaEntity.setTmdbRating(media.mediaAverageRatings().); //Bug: to solve!
//        mediaEntity.setMediaType(media.mediaType());
//
//        return mediaEntity;
//    }

//    public Media toDomain(MediaEntity mediaEntity){
//        return null; // Bug: to solve!
//        return new Media(
//                MediaId.from(mediaEntity.getId()),
//                mediaEntity.getExternalMediaId(),
//                mediaEntity.getName(),
//                mediaEntity.getReleaseDate(),
//                MediaAverageRating.of(mediaEntity.getTmdbRating()), //Bug: to solve!
//                mediaEntity.getMediaType()
//        );
//    }
}
