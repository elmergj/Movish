package io.github.elmergj.movish.api.domain.model.entity.listing;

import io.github.elmergj.movish.api.domain.exception.DomainRuleViolationException;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.shared.BaseEntity;
import io.github.elmergj.movish.api.domain.shared.UserAsset;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TitleList extends BaseEntity<TitleList, TitleListId> implements UserAsset {

    private final UserId userOwnerId;
    private final Set<TitleId> titleIdReferences;
    private final TitleListType listType;
    private final LocalDate dateCreated;
    private String name;

    private TitleList(TitleListId id, UserId userOwnerId, String name, LocalDate dateCreated, TitleListType listType,
                      Set<TitleId> titleIdReferences){
        super(id);
        this.userOwnerId = userOwnerId;
        this.name = name;
        this.dateCreated = dateCreated;
        this.listType = listType;
        this.titleIdReferences = new HashSet<>(titleIdReferences);
    }

    public static TitleList create(TitleListId id, UserId userOwnerId, String name, TitleListType listType){

        Set<TitleId> titleIds = new HashSet<>();
        LocalDate dateCreated = LocalDate.now();
        return new TitleList(id, userOwnerId, name, dateCreated, listType, titleIds);
    }

    public static TitleList fromExisting(TitleListId id, UserId userOwnerId, String name, LocalDate dateCreated,
                                         TitleListType listType,
                                         Set<TitleId> titleIds){
        return new TitleList(id, userOwnerId, name, dateCreated,listType, titleIds);
    }

    //Getters
    @Override
    public UserId getUserOwnerId(){
        return userOwnerId;
    }

    @Override
    public String getAssetName() {
        return "title list";
    }

    public String getName(){
        return name;
    }

    public LocalDate getDateCreated(){
        return dateCreated;
    }

    public Set<TitleId> getUserTitleIdReferences(){
        return Set.copyOf(titleIdReferences);
    }

    public TitleListType getListType(){
        return this.listType;
    }

    public boolean isDefaultList(){
        return listType != TitleListType.USER_CUSTOM_LIST;
    }

    // Public Methods
    public void updateListName(String name){

        if (this.isDefaultList()) throw new DomainRuleViolationException("Unable to rename this list");;

        if (name.equals(this.name)) return;

        this.name = name;
    }

    public void addUserTitle(TitleId titleId) {

        if (listType != TitleListType.USER_CUSTOM_LIST){
            throw new DomainRuleViolationException("Unable to add the user title to the list");
        }

        if (titleIdReferences.contains(titleId)){
            throw new DomainRuleViolationException("User title is already in the list");
        }
        titleIdReferences.add(titleId);
    }

    public void removeUserTitle(TitleId titleId) {

        if (listType != TitleListType.USER_CUSTOM_LIST){
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
