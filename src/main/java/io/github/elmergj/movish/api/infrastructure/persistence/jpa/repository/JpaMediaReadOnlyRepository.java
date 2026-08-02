package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import org.springframework.data.repository.Repository;

public interface JpaMediaReadOnlyRepository extends Repository<Object, String> {
//public interface JpaMediaReadOnlyRepository extends Repository<MediaEntity, String> {

//    @Query("""
//        select t.id as mediaId,
//               t.name as name,
//               t.tmdbRating as tmdbRating,
//               t.releaseDate as releaseDate
//        from MediaEntity t
//        where t.id in :ids
//        """)
//    List<MediaSummaryQueryResult> findAllByIdIn(@Param("ids") Collection<String> ids);

}
