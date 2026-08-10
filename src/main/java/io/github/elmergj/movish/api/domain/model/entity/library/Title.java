package io.github.elmergj.movish.api.domain.model.entity.library;

import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.shared.BaseEntity;
import io.github.elmergj.movish.api.domain.shared.Event;
import io.github.elmergj.movish.api.domain.shared.UserAsset;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Title extends BaseEntity<Title, TitleId> implements UserAsset {

    private final MediaId mediaId;
    private final MediaType mediaType;
    private final String name;
    private final UserId userOwnerId;
    private final LocalDate dateAdded;
    private boolean isFavorite;
    private TrackingStatus trackingStatus;
    private int timesWatched;
    private TitleUserRating titleUserRating;
    private TitleReview userReview;

    private final List<Event> events = new ArrayList<>();

    //Constructors
    private Title(TitleId id, MediaId mediaId, MediaType mediaType, String name, UserId userOwnerId, LocalDate dateAdded,
                  boolean isFavorite, TrackingStatus trackingStatus, int timesWatched,
                  TitleUserRating titleUserRating, TitleReview userReview) {
        super(id);
        this.mediaId = mediaId;
        this.mediaType = mediaType;
        this.name = name;
        this.userOwnerId = userOwnerId;
        this.isFavorite = isFavorite;
        this.dateAdded = dateAdded;
        this.trackingStatus = trackingStatus;
        this.timesWatched = timesWatched;
        this.titleUserRating = titleUserRating;
        this.userReview = userReview;
    }

    // Creator
    public static Title create(TitleId id, MediaId mediaId, String name, MediaType mediaType, UserId userId) {
        TrackingStatus trackingStatus = TrackingStatus.NOT_TRACKED;
        int timesWatched = 0;
        TitleReview userReview = TitleReview.from("No review");
        LocalDate dateAdded = LocalDate.now();

        return new Title(id, mediaId, mediaType, name, userId, dateAdded,
                false, trackingStatus, timesWatched, null, userReview);
    }

    public static Title fromExisting(
            TitleId id,
            MediaId mediaId,
            String name,
            MediaType mediaType,
            UserId userId,
            boolean isFavorite,
            LocalDate dateAdded,
            TrackingStatus trackingStatus,
            int timesWatched,
            TitleUserRating titleUserRating,
            TitleReview userReview) {

        return new Title(id, mediaId, mediaType, name, userId, dateAdded, isFavorite, trackingStatus,
                timesWatched, titleUserRating, userReview);
    }

    //Getters
    public Optional<TitleUserRating> getUserRating() {
        return Optional.ofNullable(titleUserRating);
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
    public boolean canUpdateTrackingStatus(TrackingStatus trackingStatus) {
        if (trackingStatus == this.trackingStatus) return false;

        this.trackingStatus = trackingStatus;

        return true;
    }

    public boolean canUpdateFavoriteStatus(UserId userId, boolean favorite){
        if (favorite == this.isFavorite) return false;

        if (favorite) {
            markAsFavorite(userId);
        } else {
            unmarkAsFavorite(userId);
        }

        return true;
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

    public MediaType getMediaType() {
        return mediaType;
    }

    public String getName() {
        return name;
    }
}