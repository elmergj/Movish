package io.github.elmergj.movish.api.interfaces.rest.library;

import io.github.elmergj.movish.api.application.library.command.AddTitleToLibraryCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleFavoriteStatusCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleTrackingStatusCommand;
import org.springframework.stereotype.Component;

@Component
public class LibraryCommandAssembler {

    AddTitleToLibraryCommand assemble(AddTitleToLibraryRequest request, String userId) {
        return new AddTitleToLibraryCommand(
                request.mediaId(),
                request.mediaType(),
                userId);
    }

    UpdateTitleFavoriteStatusCommand assemble(UpdateTitleFavoriteRequest request, String titleId, String userId) {
        return new UpdateTitleFavoriteStatusCommand(
                titleId,
                userId,
                request.favorite()
        );
    }

    UpdateTitleTrackingStatusCommand assemble(UpdateTitleTrackingStatusRequest request,  String titleId, String userId) {
        return new UpdateTitleTrackingStatusCommand(
                userId,
                titleId,
                request.trackingStatus()
        );
    }



}
