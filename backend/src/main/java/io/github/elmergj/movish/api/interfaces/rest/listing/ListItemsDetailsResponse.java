package io.github.elmergj.movish.api.interfaces.rest.listing;

import io.github.elmergj.movish.api.application.listing.query.ListItemsDetailsView;

import java.util.List;

public record ListItemsDetailsResponse(
        String titleListId,
        String listName,
        int totalElements,
        List<ListItemsDetailsView.Items> itemsDetails
) {
}
