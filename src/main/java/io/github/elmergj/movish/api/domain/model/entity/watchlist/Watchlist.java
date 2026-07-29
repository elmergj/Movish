package io.github.elmergj.movish.api.domain.model.entity.watchlist;

import io.github.elmergj.movish.api.domain.exception.DomainRuleViolationException;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.shared.BaseEntity;
import io.github.elmergj.movish.api.domain.shared.UserAsset;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Watchlist extends BaseEntity<Watchlist, WatchlistId> implements UserAsset {

    private final UserId userOwnerId;
    private final Set<TitleId> titleIdReferences;
    private final WatchlistType listType;
    private final LocalDate dateCreated;
    private String name;

    private Watchlist(WatchlistId id, UserId userOwnerId, String name, LocalDate dateCreated, WatchlistType listType,
                      Set<TitleId> titleIdReferences){
        super(id);
        this.userOwnerId = userOwnerId;
        this.name = name;
        this.dateCreated = dateCreated;
        this.listType = listType;
        this.titleIdReferences = new HashSet<>(titleIdReferences);
    }

    public static Watchlist create(WatchlistId id, UserId userOwnerId, String name, WatchlistType listType){

        Set<TitleId> titleIds = new HashSet<>();
        LocalDate dateCreated = LocalDate.now();
        return new Watchlist(id, userOwnerId, name, dateCreated, listType, titleIds);
    }

    public static Watchlist fromExisting(WatchlistId id, UserId userOwnerId, String name, LocalDate dateCreated,
                                         WatchlistType listType,
                                         Set<TitleId> titleIds){
        return new Watchlist(id, userOwnerId, name, dateCreated,listType, titleIds);
    }

    //Getters
    @Override
    public UserId getUserOwnerId(){
        return userOwnerId;
    }

    public String getName(){
        return name;
    }

    public LocalDate getDateCreated(){
        return dateCreated;
    }

    public Set<TitleId> getTitleIdReferences(){
        return Set.copyOf(titleIdReferences);
    }

    public WatchlistType getListType(){
        return this.listType;
    }

    public boolean isDefaultList(){
        return listType != WatchlistType.CUSTOM_USER_WATCHLIST;
    }

    // Public Methods
    public void updateListName(String name){

        if (this.isDefaultList()) throw new DomainRuleViolationException("Unable to rename this list");;

        if (name.equals(this.name)) return;

        this.name = name;
    }

    public void addTitle(TitleId titleId) {

        if (listType != WatchlistType.CUSTOM_USER_WATCHLIST){
            throw new DomainRuleViolationException("Unable to add the user title to the list");
        }

        if (titleIdReferences.contains(titleId)){
            throw new DomainRuleViolationException("User title is already in the list");
        }
        titleIdReferences.add(titleId);
    }

    public void removeTitle(TitleId titleId) {

        if (listType != WatchlistType.CUSTOM_USER_WATCHLIST){
            throw new DomainRuleViolationException("Unable to remove the user title from the list");
        }

        if (!titleIdReferences.contains(titleId)){
            throw new DomainRuleViolationException("Unable to remove user title, it is not in the list");
        }
        titleIdReferences.remove(titleId);
    }

    public void applyAddTitleToDefaultList(TitleId titleId) {

        if (titleIdReferences.contains(titleId)){
            throw new DomainRuleViolationException("Title is already in the list");
        }
        titleIdReferences.add(titleId);
    }

    public void applyRemoveTitleFromDefaultList(TitleId titleId) {

        if (!titleIdReferences.contains(titleId)){
            throw new DomainRuleViolationException("Unable to remove user title, this is not in the list");
        }
        titleIdReferences.remove(titleId);
    }

    public void canBeDeleted(){
        if(this.isDefaultList()) throw new DomainRuleViolationException("Unable to delete this list");
    }
}
