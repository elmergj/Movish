package io.github.elmergj.movish.api.domain.model.entity.library;

import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TitleTest {

    private UserId userId;
    private MediaId mediaId;
    private TitleId titleId;
    private MediaType mediaType;

    @BeforeEach
    void setUp() {
        this.userId = UserId.from("user-123");
        this.titleId = TitleId.from("title-456");
        this.mediaId = MediaId.from("media-789");
        this.mediaType = MediaType.MOVIE;
    }

    /**
     * Creates a {@link Title} instance forced into the specified favorite status for testing purposes.
     * <p>
     * <b>Domain Assumption:</b> By design, {@link Title#create} always initializes a title in a
     * <i>non-favorite</i> status with an empty event queue. If {@code isFavorite} is {@code true},
     * this helper executes the domain method to transition the state and immediately purges
     * the resulting events via {@link Title#pullEvents()}.
     * </p>
     * <p>
     * This ensures the test receives a cleanly initialized aggregate ready for the <b>When</b> phase
     * without leakage from the <b>Given</b> setup.
     * </p>
     *
     * @param isFavorite the target favorite status for the test scenario
     * @return a {@link Title} instance configured in the requested status with no pending domain events
     */
    private Title createTitleInState(boolean isFavorite) {
        var title = Title.create(titleId, mediaId, "Titanic", mediaType, userId);
        if (isFavorite) {
            title.canUpdateFavoriteStatus(userId, true);
            title.pullEvents();
        }
        return title;
    }

    @Test
    @DisplayName("Should mark as favorite and raise event when state actually changes to true")
    void should_mark_as_favorite_and_raise_event_when_currently_not_favorite() {

        var title = createTitleInState(false);

        boolean updated = title.canUpdateFavoriteStatus(userId, true);

        assertThat(updated).isTrue();
        assertThat(title.isFavorite()).isTrue();
        assertThat(title.pullEvents())
                .hasSize(1)
                .first()
                .isInstanceOf(TitleMarkedAsFavoriteEvent.class);
    }

    @Test
    @DisplayName("Should unmark as favorite and raise event when state actually changes to false")
    void should_unmark_as_favorite_and_raise_event_when_currently_favorite() {

        var title = createTitleInState(true);

        boolean updated = title.canUpdateFavoriteStatus(userId, false);

        assertThat(updated).isTrue();
        assertThat(title.isFavorite()).isFalse();
        assertThat(title.pullEvents())
                .hasSize(1)
                .first()
                .isInstanceOf(TitleUnmarkedAsFavoriteEvent.class);
    }

    @Test
    @DisplayName("Should return false and produce no events when requesting favorite=true on already favorite title")
    void should_return_false_and_no_events_when_already_favorite() {

        var title = createTitleInState(true);

        boolean updated = title.canUpdateFavoriteStatus(userId, true);

        assertThat(updated).isFalse();  
        assertThat(title.isFavorite()).isTrue();
        assertThat(title.pullEvents()).isEmpty();
    }

    @Test
    @DisplayName("Should return false and produce no events when requesting favorite=false on non-favorite title")
    void should_return_false_and_no_events_when_already_not_favorite() {

        var title = createTitleInState(false);

        boolean updated = title.canUpdateFavoriteStatus(userId, false);

        assertThat(updated).isFalse();
        assertThat(title.isFavorite()).isFalse();
        assertThat(title.pullEvents()).isEmpty();
    }

    @Test
    @DisplayName("Should update tracking status and return true when new status is different")
    void should_update_tracking_status_and_return_true_when_status_is_different() {

        var title = createTitleInState(false);
        var initialStatus = title.getTrackingStatus();
        var newStatus = TrackingStatus.WATCHING;

        boolean updated = title.canUpdateTrackingStatus(newStatus);

        assertThat(updated).isTrue();
        assertThat(title.getTrackingStatus())
                .isNotEqualTo(initialStatus)
                .isEqualTo(newStatus);
    }

    @Test
    @DisplayName("Should not update tracking status and return false when new status is identical to current")
    void should_not_update_tracking_status_and_return_false_when_status_is_identical() {

        var title = createTitleInState(false);
        var currentStatus = title.getTrackingStatus();

        boolean updated = title.canUpdateTrackingStatus(currentStatus);

        assertThat(updated).isFalse();
        assertThat(title.getTrackingStatus()).isEqualTo(currentStatus);
    }

    @Test
    @DisplayName("Should register BookUnlinkedEvent with correct bookId and userId when removed")
    void should_register_book_unlinked_event_when_removed() {

        var title = createTitleInState(false);
        var executingUserId = userId;

        title.remove(executingUserId);

        assertThat(title.pullEvents())
                .hasSize(1)
                .first()
                .isInstanceOf(TitleUnlinkedEvent.class)
                .satisfies(event -> {
                    var unlinkedEvent = (TitleUnlinkedEvent) event;
                    assertThat(unlinkedEvent.titleId()).isEqualTo(title.id());
                    assertThat(unlinkedEvent.userId()).isEqualTo(executingUserId);
                });
    }
}