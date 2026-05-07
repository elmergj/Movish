package io.github.elmergj.movish.api.domain.model.entity.listing;

import io.github.elmergj.movish.api.domain.model.entity.library.UserTitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;

import java.util.ArrayList;
import java.util.List;

public abstract class DefaultList{

    protected final TitleListId id;
    protected final UserId userOwnerId;
    protected final String name;
    protected final List<UserTitleId> userTitleIds;

    protected DefaultList(TitleListId id, UserId userOwnerId, String name) {
        this.id = id;
        this.userOwnerId = userOwnerId;
        this.name = name;
        this.userTitleIds = new ArrayList<>();
    }

    public TitleListId id() {
        return id;
    }

    public UserId getUserOwnerId(){
        return userOwnerId;
    }

    public String getName(){
        return name;
    }

    public boolean sameIdentityAs(TitleList other) {
        return other != null && other.id().equals(this.id());
    }

    public List<UserTitleId> getUserTitleIds() {
        return List.copyOf(userTitleIds);
    }

    public boolean isDefault() {
        return true;
    }
}
