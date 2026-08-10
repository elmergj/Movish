package io.github.elmergj.movish.api.interfaces.rest.watchlist;

import io.github.elmergj.movish.api.application.listing.WatchlistService;
import io.github.elmergj.movish.api.application.listing.command.AddTitleToWatchlistCommand;
import io.github.elmergj.movish.api.application.listing.command.CreateWatchlistCommand;
import io.github.elmergj.movish.api.application.listing.command.DeleteWatchlistCommand;
import io.github.elmergj.movish.api.application.listing.command.RemoveTitleFromWatchlistCommand;
import io.github.elmergj.movish.api.application.listing.command.UpdateWatchlistNameCommand;
import io.github.elmergj.movish.api.application.listing.query.WatchlistOverviewQuery;
import io.github.elmergj.movish.api.application.listing.query.WatchlistDetailsQuery;
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

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/list")
public class WatchlistController {

    private final WatchlistService watchlistService;
    private final WatchlistResponseAssembler responseAssembler;

    @PostMapping()
    public ResponseEntity<WatchlistCreationResponse> createList(
            @AuthenticationPrincipal String userId, @Valid @RequestBody CreateListRequest request){
        var command = new CreateWatchlistCommand(userId, request.name());

        var outcome = watchlistService.createCustomWatchlist(command);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uriResponse}")
                .buildAndExpand(outcome.customListId()).toUri();

        var response = new WatchlistCreationResponse(
                outcome.name(),
                outcome.dateCreated()
        );

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{listId}")
    public ResponseEntity<WatchlistOverviewResponse> getListDetails(
            @AuthenticationPrincipal String userId, @PathVariable String listId){

        var query = new WatchlistOverviewQuery(userId, listId);

        var view = watchlistService.getWatchlistOverview(query);

        var response = new WatchlistOverviewResponse(
                view.listId(),
                view.name(),
                view.totalElements()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{listId}/titles")
    public ResponseEntity<TitleAdditionResponse> addTitle(
            @AuthenticationPrincipal String userId, @PathVariable String listId, @Valid @RequestBody AddTitleToListRequest request){
        var command = new AddTitleToWatchlistCommand(userId, listId, request.titleId());

        var outcome = watchlistService.addTitleToList(command);

        var response = new TitleAdditionResponse(
                outcome.customListId(),
                outcome.totalElements(),
                "The title with titleId " + outcome.titleId() + " was successful added to the list"
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{listId}/titles")
    public ResponseEntity<WatchlistDetailsResponse> getListItemsDetails(
            @AuthenticationPrincipal String userId, @PathVariable String listId){

        var query = new WatchlistDetailsQuery(userId, listId);

        var view = watchlistService.getListItemsDetails(query);

        return ResponseEntity.ok(responseAssembler.assemble(view));
    }

    @DeleteMapping("/{listId}/title/{titleId}")
    public ResponseEntity<TitleRemovalResponse> removeTitleFromList(
            @AuthenticationPrincipal String userId, @PathVariable String listId, @PathVariable String titleId){

        var command = new RemoveTitleFromWatchlistCommand(userId, listId, titleId);

        var outcome = watchlistService.removeTitleFromList(command);

        var response = new TitleRemovalResponse(
                outcome.customListId(),
                "The title with titleId " + outcome.titleId() + " was successful removed to the list"
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{listId}")
    public ResponseEntity<ListDeletionResponse> deleteList(
            @AuthenticationPrincipal String userId, @PathVariable String listId){

        var command = new DeleteWatchlistCommand(userId, listId);

        var outcome = watchlistService.deleteWatchlist(command);

        var response = new ListDeletionResponse(
                "The " + outcome.name() + " list with titleId " + outcome.customListId() + " was successful deleted" );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{listId}/name")
    public ResponseEntity<ListNameUpdateResponse> updateCustomListName(
            @AuthenticationPrincipal String userId, @PathVariable String listId, @Valid @RequestBody ChangeListNameRequest request){

        var command = new UpdateWatchlistNameCommand(userId, listId, request.newName());

        var outcome = watchlistService.updateCustomTitleListName(command);

        var response = new ListNameUpdateResponse(
                outcome.listId(),
                outcome.name(),
                outcome.totalElements()
        );

        return ResponseEntity.ok(response);
    }
}