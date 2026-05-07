package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.model.entity.listing.TitleListType;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.TitleListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaTitleListRepository extends JpaRepository<TitleListEntity, String> {

    Optional<TitleListEntity> findByUserEntity_Id(String userEntityId);

    Optional<TitleListEntity> findByUserEntity_IdAndListTypeEquals(String userEntityId, TitleListType listType);

    boolean existsByUserEntity_IdAndName(String userEntityId, String name);

    Optional<TitleListEntity> findByIdAndUserEntity_Id(String id, String userEntityId);

    @Modifying
    @Query(value = "DELETE FROM list_titles_ids WHERE user_title_id = :userTitleId", nativeQuery = true)
    void removeReferenceFromAllLists(@Param("userTitleId") String titleId);
}
