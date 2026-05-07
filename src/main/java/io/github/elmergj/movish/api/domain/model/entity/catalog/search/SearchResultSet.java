package io.github.elmergj.movish.api.domain.model.entity.catalog.search;

import java.util.List;

/**
 * Immutable container for paginated search results.
 *
 * @param <E> Any type implementing the SearchResult interface.
 * @param items List of found itemsDetails.
 * @param currentPage Current page index.
 * @param totalElements Total count of itemsDetails available at the provider.
 * @param hasMore Indicates if there is a next page available.
 */
public record SearchResultSet<E extends SearchResult>(
        List<E> items,
        int currentPage,
        long totalElements,
        boolean hasMore
) {

    /**
     * Compact constructor to ensure the list is immutable and non-null.
     */
    public SearchResultSet {
        items = (items != null) ? List.copyOf(items) : List.of();
    }
}