package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.application.library.query.UserTitleSummaryQueryResult;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.UserTitleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface JpaUserTitleReadOnlyRepository extends Repository<UserTitleEntity, String> {

    @Query("""
        SELECT ut.id as userTitleId,
               ut.titleId as titleId,
               ut.status as trackingStatus,
               ut.userTitleRating as userTitleRating
        FROM UserTitleEntity ut
        WHERE ut.id IN :ids
        """)
    List<UserTitleSummaryQueryResult> findAllByIdIn(@Param("ids") Collection<String> ids);
}
