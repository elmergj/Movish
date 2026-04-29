package io.github.elmergj.movish.api.interfaces.rest.listing;

import io.github.elmergj.movish.api.application.listing.ListingService;
import io.github.elmergj.movish.api.application.listing.command.AddTitleToListCommand;
import io.github.elmergj.movish.api.application.listing.command.CreateCustomTitleListCommand;
import io.github.elmergj.movish.api.application.listing.command.DeleteCustomListCommand;
import io.github.elmergj.movish.api.application.listing.command.RemoveTitleFromListCommand;
import io.github.elmergj.movish.api.application.listing.command.UpdateCustomListNameCommand;
import io.github.elmergj.movish.api.application.listing.query.ListDetailsQuery;
import io.github.elmergj.movish.api.application.listing.query.ListItemsQuery;
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
public class ListingController {

    private final ListingService listingService;

    @PostMapping()
    public ResponseEntity<ListCreationResponse> qcreateList(
            @AuthenticationPrincipal String userId, @Valid @RequestBody CreateListRequest request){
        var command = new CreateCustomTitleListCommand(userId, request.name());

        var outcome = listingService.createCustomTitleList(command);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{uriResponse}")
                .buildAndExpand(outcome.customListId()).toUri();

        var response = new ListCreationResponse(
                outcome.name(),
                outcome.dateCreated()
        );

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{listId}")
    public ResponseEntity<ListDetailsResponse> getListDetails(
            @AuthenticationPrincipal String userId, @PathVariable String listId){

        var query = new ListDetailsQuery(userId, listId);

        var view = listingService.getListDetails(query);

        var response = new ListDetailsResponse(
                view.listId(),
                view.name(),
                view.totalElements()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{listId}/titles")
    public ResponseEntity<TitleAdditionResponse> addTitle(
            @AuthenticationPrincipal String userId, @PathVariable String listId, @Valid @RequestBody AddTitleToListRequest request){
        var command = new AddTitleToListCommand(userId, listId, request.titleId());

        var outcome = listingService.addTitleToList(command);

        var response = new TitleAdditionResponse(
                outcome.customListId(),
                outcome.totalElements(),
                "The title with id " + outcome.userTitleId() + " was successful added to the list"
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{listId}/titles")
    public ResponseEntity<ListItemsDetailsResponse> getListItemsDetails(
            @AuthenticationPrincipal String userId, @PathVariable String listId){

        var query = new ListItemsQuery(userId, listId);

        var view = listingService.getListItemsDetails(query);

        var response = new ListItemsDetailsResponse(
                view.titleListId(),
                view.listName(),
                view.totalElements(),
                view.itemsDetails()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{listId}/title/{titleId}")
    public ResponseEntity<TitleRemovalResponse> removeTitleFromList(
            @AuthenticationPrincipal String userId, @PathVariable String listId, @PathVariable String titleId){

        var command = new RemoveTitleFromListCommand(userId, listId, titleId);

        var outcome = listingService.removeTitleFromList(command);

        var response = new TitleRemovalResponse(
                outcome.customListId(),
                "The title with id " + outcome.userTitleId() + " was successful removed to the list"
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{listId}")
    public ResponseEntity<ListDeletionResponse> deleteList(
            @AuthenticationPrincipal String userId, @PathVariable String listId){

        var command = new DeleteCustomListCommand(userId, listId);

        var outcome = listingService.deleteCustomTitleList(command);

        var response = new ListDeletionResponse(
                "The " + outcome.name() + " list with id " + outcome.customListId() + " was successful deleted" );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{listId}/name")
    public ResponseEntity<ListNameUpdateResponse> updateCustomListName(
            @AuthenticationPrincipal String userId, @PathVariable String listId, @Valid @RequestBody ChangeListNameRequest request){

        var command = new UpdateCustomListNameCommand(userId, listId, request.newName());

        var outcome = listingService.updateCustomTitleListName(command);

        var response = new ListNameUpdateResponse(
                outcome.listId(),
                outcome.name(),
                outcome.totalElements()
        );

        return ResponseEntity.ok(response);
    }
}