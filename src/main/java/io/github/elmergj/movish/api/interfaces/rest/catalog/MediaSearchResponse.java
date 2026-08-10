package io.github.elmergj.movish.api.interfaces.rest.catalog;

public record MediaSearchResponse(
        //Optimize: Map to a better http response body, is the page and total itemsDetails part of the response or the search results?
//        int page,
//        List<MediaSummaryResult> itemsDetails,
//        long total_results,
//        long total_pages
//        SearchResultSet<MediaSummaryContent> results
) {
    public record MediaSummaryContent(
            String title,
            String thumbnail,
            Double Rating,
            String mediaType,
            String releaseDate
    ) {
    }
}
