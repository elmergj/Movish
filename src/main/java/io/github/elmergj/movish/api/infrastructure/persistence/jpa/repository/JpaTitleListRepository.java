package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.model.entity.watchlist.WatchlistType;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.WatchlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaTitleListRepository extends JpaRepository<WatchlistEntity, String> {

    Optional<WatchlistEntity> findByUserEntity_Id(String userEntityId);

    Optional<WatchlistEntity> findByUserEntity_IdAndListTypeEquals(String userEntityId, WatchlistType listType);

    boolean existsByUserEntity_IdAndName(String userEntityId, String name);

    Optional<WatchlistEntity> findByIdAndUserEntity_Id(String id, String userEntityId);

    @Modifying
    @Query(value = "DELETE FROM watchlist_title_ids WHERE title_id = :titleId", nativeQuery = true)
    void removeReferenceFromAllLists(@Param("titleId") String titleId);
}
