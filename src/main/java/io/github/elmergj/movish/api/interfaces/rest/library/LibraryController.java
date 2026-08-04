package io.github.elmergj.movish.api.interfaces.rest.library;

import io.github.elmergj.movish.api.application.Result.FailureResult;
import io.github.elmergj.movish.api.application.Result.SuccessResult;
import io.github.elmergj.movish.api.application.library.LibraryService;
import io.github.elmergj.movish.api.application.library.command.AddTitleToLibraryCommand;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementFailure.TitleAlreadyInLibrary;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementOutcome.TitleAdditionOutcome;
import io.github.elmergj.movish.api.application.library.command.RemoveTitleCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleFavoriteStatusCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleTrackingStatusCommand;
import io.github.elmergj.movish.api.application.library.query.TitleDetailsQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library/")
public class LibraryController {

    private final LibraryService libraryService;
    private final LibraryCommandAssembler commandAssembler;
    private final LibraryResponseAssembler responseAssembler;

    @PostMapping("/title")
//    public ResponseEntity<ApiResponse or TitleAdditionResponse{I}> addTitleToLibrary(
    public ResponseEntity<?> addTitleToLibrary(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody AddTitleToLibraryRequest request) {

        AddTitleToLibraryCommand command = commandAssembler.assemble(request, userId);

        var outcome = libraryService.addTitleToLibrary(command);

        return switch (outcome){
            case SuccessResult(TitleAdditionOutcome result) ->
                    ResponseEntity.created(
                            ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{uriResponse}")
                                    .buildAndExpand(result.titleId()).toUri())
                            .body(responseAssembler.assemble(result));
            case FailureResult(TitleAlreadyInLibrary failure) -> ResponseEntity.badRequest().body("The title with media id " + failure.titleId() + " already exists");
        };
    }

    @GetMapping("/{titleId}")
    public ResponseEntity<TitleDetailsResponse> getTitleDetails(
            @AuthenticationPrincipal String userId,
            @PathVariable String titleId){

        var query = new TitleDetailsQuery(userId, titleId);

        var view = libraryService.getTitleDetails(query);

        var response = new TitleDetailsResponse(
                view.titleId(),
                view.trackingStatus(),
                view.dateAdded(),
                view.isFavorite(),
                view.titleDetails()
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/title/{titleId}/favorite")
    public ResponseEntity<TitleFavoriteStatusResponse> updateFavoriteTitle(
            @AuthenticationPrincipal String userId,
            @PathVariable String titleId,
            @Valid @RequestBody UpdateTitleFavoriteRequest request){

        var command = new UpdateTitleFavoriteStatusCommand(titleId, userId, request.favorite());

        var outcome = libraryService.updateTitleFavoriteStatus(command);

        var response = new TitleFavoriteStatusResponse(
                null, // Bug: to solve!
                false // Bug: to solve!
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/title/{titleId}/tracking")
    public ResponseEntity<UpdateTitleTrackingStatusResponse> updateTrackingStatus(
            @AuthenticationPrincipal String userId,
            @PathVariable String titleId,
            @Valid @RequestBody UpdateTitleTrackingStatusRequest request){

        var command = new UpdateTitleTrackingStatusCommand(userId, titleId, request.trackingStatus());

        var outcome = libraryService.updateTitleTrackingStatus(command);

        var response = new UpdateTitleTrackingStatusResponse(
                null, // Bug: to solve!
                "The title with id " + titleId + " was successful updated");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/title/{titleId}")
    public ResponseEntity<RemoveTitleResponse> removeTitle(
            @AuthenticationPrincipal String userId,
            @PathVariable String titleId){

        var command = new RemoveTitleCommand(userId, titleId);

        var outcome = libraryService.deleteTitle(command);

        var response = new RemoveTitleResponse(
                "The title with id " + "outcome.titleId()" + " was successful deleted from the library"); // Bug: to solve!

        return ResponseEntity.ok(response);
    }
}
