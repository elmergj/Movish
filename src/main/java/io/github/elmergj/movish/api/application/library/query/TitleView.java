package io.github.elmergj.movish.api.application.library.query;

public sealed interface TitleView {

    record TitleDetailsView(
            String titleId,
            String trackingStatus,
            String dateAdded,
            boolean isFavorite,
            TitleDetails titleDetails
    ) implements TitleView {

        public record TitleDetails(
                String mediaId,
                String name,
                String releaseDate
        ) {
        }
    }
}
