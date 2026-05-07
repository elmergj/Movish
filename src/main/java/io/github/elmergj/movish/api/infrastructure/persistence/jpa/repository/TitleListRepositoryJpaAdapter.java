package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.model.entity.library.UserTitleId;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleList;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleListId;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleListType;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.repository.TitleListRepository;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers.TitleListJpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TitleListRepositoryJpaAdapter implements TitleListRepository {

    private final JpaTitleListRepository jpaTitleListRepository;
    private final TitleListJpaMapper titleListJpaMapper;

    @Override
    public void save(TitleList titleList) {
        jpaTitleListRepository.save(titleListJpaMapper.toJpaTitleList(titleList));
    }

    @Override
    public Optional<TitleList> findById(TitleListId id) {
        return jpaTitleListRepository.findById(id.value())
                .map(titleListJpaMapper::toDomain);
    }

    @Override
    public Optional<TitleList> findByIdAndUserOwnerId(TitleListId id, UserId userId) {
        return jpaTitleListRepository.findByIdAndUserEntity_Id(id.value(), userId.value())
                .map(titleListJpaMapper::toDomain);
    }

    @Override
    public Optional<TitleList> findByUserOwnerId(UserId userId) {
        return jpaTitleListRepository.findByUserEntity_Id(userId.value())
                .map(titleListJpaMapper::toDomain);
    }

    @Override
    public void removeReferenceFromAllLists(UserTitleId userTitleId) {
        jpaTitleListRepository.removeReferenceFromAllLists(userTitleId.value());
    }

    @Override
    public Optional<TitleList> findByUserOwnerIdAndListType(UserId userId, TitleListType titleListType) {
        return jpaTitleListRepository.findByUserEntity_IdAndListTypeEquals(userId.value(), titleListType)
                .map(titleListJpaMapper::toDomain);
    }

    @Override
    public boolean existByUserOwnerIdAndListName(UserId userId, String name) {
        return jpaTitleListRepository.existsByUserEntity_IdAndName(userId.value(), name);
    }

    @Override
    public void delete(TitleList titleList) {
        jpaTitleListRepository.delete(titleListJpaMapper.toJpaTitleList(titleList));
    }
}
