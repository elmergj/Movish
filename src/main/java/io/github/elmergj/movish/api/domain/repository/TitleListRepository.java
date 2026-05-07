package io.github.elmergj.movish.api.domain.repository;

import io.github.elmergj.movish.api.domain.model.entity.library.UserTitleId;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleList;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleListId;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleListType;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;

import java.util.Optional;

public interface TitleListRepository {

    void save(TitleList list);

    Optional<TitleList> findById(TitleListId id);

    Optional<TitleList> findByIdAndUserOwnerId(TitleListId id, UserId userId);

    Optional<TitleList> findByUserOwnerId(UserId userId);

    void removeReferenceFromAllLists(UserTitleId userTitleId);

    Optional<TitleList> findByUserOwnerIdAndListType(UserId userId, TitleListType titleListType);

    boolean existByUserOwnerIdAndListName(UserId userId, String name);

    void delete(TitleList titleList);
}
