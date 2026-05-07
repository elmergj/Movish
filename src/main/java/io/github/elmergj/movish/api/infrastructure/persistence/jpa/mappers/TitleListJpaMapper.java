package io.github.elmergj.movish.api.infrastructure.persistence.jpa.mappers;

import io.github.elmergj.movish.api.domain.model.entity.library.UserTitleId;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleList;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleListId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.TitleListEntity;
import io.github.elmergj.movish.api.infrastructure.persistence.jpa.entity.UserEntity;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TitleListJpaMapper {

    public @NonNull TitleListEntity toJpaTitleList(TitleList titleList){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(titleList.getUserOwnerId().value());

        TitleListEntity titleListEntity = new TitleListEntity();
        titleListEntity.setUserEntity(userEntity);

        titleListEntity.setId(titleList.id().value());
        titleListEntity.setName(titleList.getName());
        titleListEntity.setDateCreated(titleList.getDateCreated());
        titleListEntity.setListType(titleList.getListType());
        titleListEntity.setUserTitleIds(titleList.getUserTitleIdReferences().stream()
                .map(UserTitleId::value)
                .collect(Collectors.toSet())
        );

        return titleListEntity;
    }

    public TitleList toDomain(TitleListEntity jpaTitleList){
        return TitleList.fromExisting(
                TitleListId.from(jpaTitleList.getId()),
                UserId.from(jpaTitleList.getUserEntity().getId()),
                jpaTitleList.getName(),
                jpaTitleList.getDateCreated(),
                jpaTitleList.getListType(),
                //Passing a mutable list
                jpaTitleList.getUserTitleIds().stream().map(UserTitleId::from)
                        .collect(Collectors.toSet())
        );
    }
}
