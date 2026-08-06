package io.github.elmergj.movish.api.interfaces.rest.library;

import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleAdditionOutcome;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleFavoriteOutcome;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleTrackingUpdateOutcome;
import io.github.elmergj.movish.api.application.library.query.TitleView.TitleDetailsView;
import io.github.elmergj.movish.api.interfaces.rest.library.TitleDetailsResponse.TitleDetailsContent;
import org.springframework.stereotype.Component;

@Component
public class LibraryResponseAssembler {

    TitleCreationResponse assemble(TitleAdditionOutcome outcome) {
        return new TitleCreationResponse(
                outcome.titleId(),
                outcome.mediaId(),
                outcome.titleName(),
                outcome.dateAdded()
        );
    }

    TitleDetailsResponse assemble(TitleDetailsView view) {
        return new TitleDetailsResponse(
                view.titleId(),
                view.trackingStatus(),
                view.dateAdded(),
                view.isFavorite(),
                new TitleDetailsContent(
                        view.titleDetails().mediaId(),
                        view.titleDetails().name(),
                        view.titleDetails().releaseDate()
                )
        );
    }

    TitleFavoriteStatusResponse assemble(TitleFavoriteOutcome outcome) {
        return new TitleFavoriteStatusResponse(
                outcome.titleId(),
                outcome.isFavorite()
        );
    }

    UpdateTitleTrackingStatusResponse assemble(TitleTrackingUpdateOutcome outcome) {
        return new UpdateTitleTrackingStatusResponse(
                outcome.titleId(),
                outcome.trackingStatus()
        );
    }
}
