package io.github.elmergj.movish.api.infrastructure.persistence.jpa.repository;

import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.Watchlist;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.WatchlistId;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.WatchlistType;
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
    public void save(Watchlist watchlist) {
        jpaTitleListRepository.save(titleListJpaMapper.toJpaTitleList(watchlist));
    }

    @Override
    public Optional<Watchlist> findById(WatchlistId id) {
        return jpaTitleListRepository.findById(id.value())
                .map(titleListJpaMapper::toDomain);
    }

    @Override
    public Optional<Watchlist> findByIdAndUserOwnerId(WatchlistId id, UserId userId) {
        return jpaTitleListRepository.findByIdAndUserEntity_Id(id.value(), userId.value())
                .map(titleListJpaMapper::toDomain);
    }

    @Override
    public Optional<Watchlist> findByUserOwnerId(UserId userId) {
        return jpaTitleListRepository.findByUserEntity_Id(userId.value())
                .map(titleListJpaMapper::toDomain);
    }

    @Override
    public void removeReferenceFromAllLists(TitleId titleId) {
        jpaTitleListRepository.removeReferenceFromAllLists(titleId.value());
    }

    @Override
    public Optional<Watchlist> findByUserOwnerIdAndListType(UserId userId, WatchlistType watchlistType) {
        return jpaTitleListRepository.findByUserEntity_IdAndListTypeEquals(userId.value(), watchlistType)
                .map(titleListJpaMapper::toDomain);
    }

    @Override
    public boolean existByUserOwnerIdAndListName(UserId userId, String name) {
        return jpaTitleListRepository.existsByUserEntity_IdAndName(userId.value(), name);
    }

    @Override
    public void delete(Watchlist watchlist) {
        jpaTitleListRepository.delete(titleListJpaMapper.toJpaTitleList(watchlist));
    }
}
