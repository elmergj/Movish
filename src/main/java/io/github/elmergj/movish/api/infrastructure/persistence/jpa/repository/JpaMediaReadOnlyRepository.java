package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.application.catalog.query.MediaSummaryQueryResult;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.MediaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface JpaMediaReadOnlyRepository extends Repository<MediaEntity, String> {

    @Query("""
        SELECT t.id as mediaId,
               t.name as name,
               t.tmdbRating as tmdbRating,
               t.releaseDate as releaseDate
        FROM MediaEntity t
        WHERE t.id IN :ids
        """)
    List<MediaSummaryQueryResult> findAllByIdIn(@Param("ids") Collection<String> ids);
}
