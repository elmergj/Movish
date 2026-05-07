package io.github.elmergj.movish.api.domain.model.entity.listing;

import io.github.elmergj.movish.api.domain.exception.DomainRuleViolationException;
import io.github.elmergj.movish.api.domain.model.entity.library.UserTitleId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.shared.BaseEntity;
import io.github.elmergj.movish.api.domain.shared.UserAsset;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TitleList extends BaseEntity<TitleList, TitleListId> implements UserAsset {

    private final UserId userOwnerId;
    private final Set<UserTitleId> userTitleIdReferences;
    private final TitleListType listType;
    private final LocalDate dateCreated;
    private String name;

    private TitleList(TitleListId id, UserId userOwnerId, String name, LocalDate dateCreated, TitleListType listType,
                      Set<UserTitleId> userTitleIdReferences){
        super(id);
        this.userOwnerId = userOwnerId;
        this.name = name;
        this.dateCreated = dateCreated;
        this.listType = listType;
        this.userTitleIdReferences = new HashSet<>(userTitleIdReferences);
    }

    public static TitleList create(TitleListId id, UserId userOwnerId, String name, TitleListType listType){

        Set<UserTitleId> userTitleIds = new HashSet<>();
        LocalDate dateCreated = LocalDate.now();
        return new TitleList(id, userOwnerId, name, dateCreated, listType, userTitleIds);
    }

    public static TitleList fromExisting(TitleListId id, UserId userOwnerId, String name, LocalDate dateCreated,
                                         TitleListType listType,
                                         Set<UserTitleId> userTitleIds){
        return new TitleList(id, userOwnerId, name, dateCreated,listType, userTitleIds);
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

    public Set<UserTitleId> getUserTitleIdReferences(){
        return Set.copyOf(userTitleIdReferences);
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

    public void addUserTitle(UserTitleId userTitleId) {

        if (listType != TitleListType.USER_CUSTOM_LIST){
            throw new DomainRuleViolationException("Unable to add the user title to the list");
        }

        if (userTitleIdReferences.contains(userTitleId)){
            throw new DomainRuleViolationException("User title is already in the list");
        }
        userTitleIdReferences.add(userTitleId);
    }

    public void removeUserTitle(UserTitleId userTitleId) {

        if (listType != TitleListType.USER_CUSTOM_LIST){
            throw new DomainRuleViolationException("Unable to remove the user title from the list");
        }

        if (!userTitleIdReferences.contains(userTitleId)){
            throw new DomainRuleViolationException("Unable to remove user title, it is not in the list");
        }
        userTitleIdReferences.remove(userTitleId);
    }

    public void applyAddTitleToDefaultList(UserTitleId userTitleId) {

        if (userTitleIdReferences.contains(userTitleId)){
            throw new DomainRuleViolationException("UserTitle is already in the list");
        }
        userTitleIdReferences.add(userTitleId);
    }

    public void applyRemoveTitleFromDefaultList(UserTitleId userTitleId) {

        if (!userTitleIdReferences.contains(userTitleId)){
            throw new DomainRuleViolationException("Unable to remove user title, this is not in the list");
        }
        userTitleIdReferences.remove(userTitleId);
    }

    public void canBeDeleted(){
        if(this.isDefaultList()) throw new DomainRuleViolationException("Unable to delete this list");
    }
}
