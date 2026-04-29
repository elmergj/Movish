package io.github.elmergj.movish.api.domain.model.entity.library;

import io.github.elmergj.movish.api.domain.model.entity.catalog.title.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.TitleReview;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.shared.BaseEntity;
import io.github.elmergj.movish.api.domain.shared.Event;
import io.github.elmergj.movish.api.domain.shared.UserAsset;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class UserTitle extends BaseEntity<UserTitle, UserTitleId> implements UserAsset {

    private final TitleId titleId;
    private final UserId userOwnerId;
    private final LocalDate dateAdded;
    private boolean isFavorite;
    private TrackingStatus trackingStatus;
    private int timesWatched;
    private UserTitleRating userTitleRating;
    private TitleReview userReview;

    private final List<Event> events = new ArrayList<>();

    //Constructors
    private UserTitle(UserTitleId id, TitleId titleId, UserId userOwnerId, LocalDate dateAdded,
                      boolean isFavorite, TrackingStatus trackingStatus, int timesWatched,
                      UserTitleRating userTitleRating, TitleReview userReview) {
        super(id);
        this.titleId = titleId;
        this.userOwnerId = userOwnerId;
        this.isFavorite = isFavorite;
        this.dateAdded = dateAdded;
        this.trackingStatus = trackingStatus;
        this.timesWatched = timesWatched;
        this.userTitleRating = userTitleRating;
        this.userReview = userReview;
    }

    // Creator
    public static UserTitle create(UserTitleId id, TitleId externalTitleId, UserId userId) {
        TrackingStatus trackingStatus = TrackingStatus.NOT_TRACKED;
        int timesWatched = 0;
        UserTitleRating userTitleRating = UserTitleRating.of(1);
        TitleReview userReview = TitleReview.from("No reviewed");
        LocalDate dateAdded = LocalDate.now();

        return new UserTitle(id, externalTitleId, userId, dateAdded, false, trackingStatus,
                timesWatched, userTitleRating, userReview);
    }

    public static UserTitle fromExisting(
            UserTitleId id,
            TitleId externalTitleId,
            UserId userId,
            boolean isFavorite,
            LocalDate dateAdded,
            TrackingStatus trackingStatus,
            int timesWatched,
            UserTitleRating userTitleRating,
            TitleReview userReview) {

        return new UserTitle(id, externalTitleId, userId, dateAdded, isFavorite, trackingStatus, timesWatched, userTitleRating,
                userReview);
    }

    //Getters
    public UserTitleRating getUserRating() {
        return userTitleRating;
    }

    public TitleReview getUserReview() {
        return userReview;
    }

    public TitleId getTitleId() {
        return titleId;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public TrackingStatus getTrackingStatus() {
        return trackingStatus;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getTimesWatched() {
        return timesWatched;
    }

    @Override
    public UserId getUserOwnerId() {
        return userOwnerId;
    }

    @Override
    public String getAssetName() {
        return "user title";
    }

    // Methods
    public void updateTrackingStatus(TrackingStatus trackingStatus) {
        this.trackingStatus = trackingStatus;
    }

    public void updateFavoriteStatus(UserId userId, boolean favorite){
        if (this.isFavorite == favorite) return;

        if (favorite) {
            markAsFavorite(userId);
        } else {
            unmarkAsFavorite(userId);
        }
    }

    private void markAsFavorite(UserId userId) {
        this.isFavorite = true;
        this.events.add(new UserTitleMarkedAsFavoriteEvent(this.id, userId, true));
    }

    private void unmarkAsFavorite(UserId userId){
        this.isFavorite = false;
        this.events.add(new UserTitleUnmarkedAsFavoriteEvent(this.id, userId, false));
    }

    public void increaseTimesWatched() {
        this.timesWatched++;
    }

    public void decreaseTimesWatched() {
        this.timesWatched--;
    }

    public void setTimesWatched(int timesWatched) {
        this.timesWatched = timesWatched;
    }

    public void setUserRating(UserTitleRating userTitleRating) {
        this.userTitleRating = userTitleRating;
    }

    public void addUserComment(TitleReview userReview) {
        this.userReview = userReview;
    }

    public void delete(UserId userId){
        this.events.add(new UserTitleDeletionEvent(this.id, userId));
    }

    // Events Register
    public List<Event> pullEvents(){
        List<Event> taken = new ArrayList<>(events);
        events.clear();
        return taken;
    }
}