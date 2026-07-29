package io.github.elmergj.movish.api.interfaces.rest.library;

import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleAdditionOutcome;
import org.springframework.stereotype.Component;

@Component
public class LibraryResponseAssembler {

    TitleCreationResponse assemble(TitleAdditionOutcome outcome) {
        return new TitleCreationResponse(
                outcome.titleId(),
                outcome.externalIdReferences().stream()
                        .map(ref -> new MediaExternalIdPair(
                                ref.mediaProvider(),
                                ref.value()))
                        .toList(),
                outcome.titleName(),
                outcome.dateAdded()
        );
    }
}
