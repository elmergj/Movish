package io.github.elmergj.movish.api.domain.model.entity.library;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaId;
import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.shared.BaseEntity;
import io.github.elmergj.movish.api.domain.shared.Event;
import io.github.elmergj.movish.api.domain.shared.UserAsset;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class Title extends BaseEntity<Title, TitleId> implements UserAsset {

    private final MediaId mediaId;
    private final UserId userOwnerId;
    private final LocalDate dateAdded;
    private boolean isFavorite;
    private TrackingStatus trackingStatus;
    private int timesWatched;
    private TitleUserRating titleUserRating;
    private TitleReview userReview;

    private final List<Event> events = new ArrayList<>();

    //Constructors
    private Title(TitleId id, MediaId mediaId, UserId userOwnerId, LocalDate dateAdded,
                  boolean isFavorite, TrackingStatus trackingStatus, int timesWatched,
                  TitleUserRating titleUserRating, TitleReview userReview) {
        super(id);
        this.mediaId = mediaId;
        this.userOwnerId = userOwnerId;
        this.isFavorite = isFavorite;
        this.dateAdded = dateAdded;
        this.trackingStatus = trackingStatus;
        this.timesWatched = timesWatched;
        this.titleUserRating = titleUserRating;
        this.userReview = userReview;
    }

    // Creator
    public static Title create(TitleId id, MediaId externalMediaId, UserId userId) {
        TrackingStatus trackingStatus = TrackingStatus.NOT_TRACKED;
        int timesWatched = 0;
        TitleUserRating titleUserRating = TitleUserRating.of(1);
        TitleReview userReview = TitleReview.from("No review");
        LocalDate dateAdded = LocalDate.now();

        return new Title(id, externalMediaId, userId, dateAdded, false, trackingStatus,
                timesWatched, titleUserRating, userReview);
    }

    public static Title fromExisting(
            TitleId id,
            MediaId mediaId,
            UserId userId,
            boolean isFavorite,
            LocalDate dateAdded,
            TrackingStatus trackingStatus,
            int timesWatched,
            TitleUserRating titleUserRating,
            TitleReview userReview) {

        return new Title(id, mediaId, userId, dateAdded, isFavorite, trackingStatus, timesWatched, titleUserRating,
                userReview);
    }

    //Getters
    public TitleUserRating getUserRating() {
        return titleUserRating;
    }

    public TitleReview getUserReview() {
        return userReview;
    }

    public MediaId getMediaId() {
        return mediaId;
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
        this.events.add(new TitleMarkedAsFavoriteEvent(this.id, userId, true));
    }

    private void unmarkAsFavorite(UserId userId){
        this.isFavorite = false;
        this.events.add(new TitleUnmarkedAsFavoriteEvent(this.id, userId, false));
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

    public void setUserRating(TitleUserRating titleUserRating) {
        this.titleUserRating = titleUserRating;
    }

    public void addUserComment(TitleReview userReview) {
        this.userReview = userReview;
    }

    public void remove(UserId userId){
        this.events.add(new TitleUnlinkedEvent(this.id, userId));
    }

    // Events Register
    public List<Event> pullEvents(){
        List<Event> taken = new ArrayList<>(events);
        events.clear();
        return taken;
    }
}