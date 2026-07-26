package io.github.elmergj.movish.api.interfaces.rest.library;

import io.github.elmergj.movish.api.application.Result.FailureResult;
import io.github.elmergj.movish.api.application.Result.SuccessResult;
import io.github.elmergj.movish.api.application.library.LibraryService;
import io.github.elmergj.movish.api.application.library.command.DeleteUserTitleCommand;
import io.github.elmergj.movish.api.application.library.command.LibraryManagementFailure.UserTitleAlreadyInLibrary;
import io.github.elmergj.movish.api.application.library.command.SaveTitleToLibraryCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleFavoriteStatusCommand;
import io.github.elmergj.movish.api.application.library.command.UpdateTitleTrackingStatusCommand;
import io.github.elmergj.movish.api.application.library.query.UserTitleDetailsQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
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
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/library/")
public class LibraryController {

    private final LibraryService libraryService;
    private final ResponseEntityExceptionHandler responseEntityExceptionHandler;

    @PostMapping("/title")
//    public ResponseEntity<ApiResponse> addTitleToLibrary(
    public ResponseEntity<?> addTitleToLibrary(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody AddTitleToLibraryRequest request) {

        var command = new SaveTitleToLibraryCommand(request.externalTitleId(), request.mediaType(), userId);

        var outcome = libraryService.createUserTitle(command);

        return switch (outcome){
            case SuccessResult(var result) ->
                    ResponseEntity.created(
                            ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{uriResponse}")
                                    .buildAndExpand(result.id()).toUri())
                            .body(new UserTitleCreationResponse(
                                    result.tmdbId(),
                                    result.titleName(),
                                    result.TrackingStatus(),
                                    result.dateAdded(),
                                    result.tmdbRating()));
            case FailureResult(UserTitleAlreadyInLibrary reason) -> ResponseEntity.badRequest().body("The title with id" + reason.userTitleId() + " already exists");
        };
    }

    @GetMapping("/{titleId}")
    public ResponseEntity<UserTitleDetailsResponse> getTitleDetails(
            @AuthenticationPrincipal String userId,
            @PathVariable String titleId){

        var query = new UserTitleDetailsQuery(userId, titleId);

        var view = libraryService.getUserTitleDetails(query);

        var response = new UserTitleDetailsResponse(
                view.userTitleId(),
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
            @Valid @RequestBody MarkTitleAsFavoriteRequest request){

        var command = new UpdateTitleFavoriteStatusCommand(titleId, userId, request.favorite());

        var outcome = libraryService.updateUserTitleFavoriteStatus(command);

        var response = new TitleFavoriteStatusResponse(
                outcome.externalTitleId(),
                outcome.isFavorite()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/title/{titleId}/tracking")
    public ResponseEntity<UpdateTitleTrackingStatusResponse> updateTrackingStatus(
            @AuthenticationPrincipal String userId,
            @PathVariable String titleId,
            @Valid @RequestBody ChangeTitleTrackingStatusRequest request){

        var command = new UpdateTitleTrackingStatusCommand(userId, titleId, request.trackingStatus());

        var outcome = libraryService.updateUserTitleTrackingStatus(command);

        var response = new UpdateTitleTrackingStatusResponse(
                outcome.trackingStatus(),
                "The title with id " + titleId + " was successful updated");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/title/{titleId}")
    public ResponseEntity<DeleteTitleResponse> deleteTitle(
            @AuthenticationPrincipal String userId,
            @PathVariable String titleId){

        var command = new DeleteUserTitleCommand(userId, titleId);

        var outcome = libraryService.deleteUserTitle(command);

        var response = new DeleteTitleResponse(
                "The title with id " + outcome.userTitleId() + " was successful deleted from the library");

        return ResponseEntity.ok(response);
    }
}
