package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.application.library.query.TitleSummaryQueryResult;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.TitleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface JpaTitleReadOnlyRepository extends Repository<TitleEntity, String> {

    @Query("""
        SELECT t.id as titleId,
               t.mediaId as mediaId,
               t.status as trackingStatus,
               t.titleUserRating as titleUserRating
        FROM TitleEntity t
        WHERE t.id IN :ids
        """)
    List<TitleSummaryQueryResult> findAllByIdIn(@Param("ids") Collection<String> ids);
}
