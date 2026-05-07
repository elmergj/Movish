package io.github.elmergj.movish.api.application.listing.query;

import java.time.LocalDate;
import java.util.List;

public record ListItemsDetailsView(
        String titleListId,
        String listName,
        int totalElements,
        List<Items> itemsDetails
) {
    public record Items(
            String userTitleId,
            String name,
            String trackingStatus,
            double userRating,
            double tmdbRating,
            LocalDate releaseDate
    ) {
    }
}