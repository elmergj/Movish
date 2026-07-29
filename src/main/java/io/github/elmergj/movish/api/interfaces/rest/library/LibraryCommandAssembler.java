package io.github.elmergj.movish.api.interfaces.rest.library;

import io.github.elmergj.movish.api.application.library.MediaExternalReference;
import io.github.elmergj.movish.api.application.library.command.AddTitleToLibraryCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleFavoriteStatusCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleTrackingStatusCommand;
import org.springframework.stereotype.Component;

@Component
public class LibraryCommandAssembler {

    AddTitleToLibraryCommand assemble(AddTitleToLibraryRequest request, String userId) {
        return new AddTitleToLibraryCommand(
                request.mediaExternalIdPairs().stream()
                        .map(pair -> new MediaExternalReference(
                                pair.providerName(),
                                pair.value()))
                        .toList(),
                request.mediaType(),
                userId);
    }

    UpdateTitleFavoriteStatusCommand assemble(UpdateTitleFavoriteRequest request) {
        return new UpdateTitleFavoriteStatusCommand();
    }

    UpdateTitleTrackingStatusCommand assemble(UpdateTitleTrackingStatusRequest request) {
        return new UpdateTitleTrackingStatusCommand();
    }



}
