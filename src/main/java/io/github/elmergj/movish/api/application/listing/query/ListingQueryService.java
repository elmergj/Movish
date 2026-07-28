package io.github.elmergj.movish.api.application.listing.query;

import io.github.elmergj.movish.api.application.catalog.query.CatalogMediaQueries;
import io.github.elmergj.movish.api.application.catalog.query.MediaSummaryQueryResult;
import io.github.elmergj.movish.api.application.library.query.TitleQueries;
import io.github.elmergj.movish.api.application.library.query.TitleSummaryQueryResult;
import io.github.elmergj.movish.api.domain.model.entity.library.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.watchlist.Watchlist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListingQueryService {

    private final TitleQueries titleQueries;
    private final CatalogMediaQueries catalogMediaQueries;

    public ListItemsDetailsView getListItemsDetails(Watchlist watchlist){

        List<TitleSummaryQueryResult> userTitleSummary =
                titleQueries.getTitleSummaryMatching(watchlist.getUserTitleIdReferences()
                        .stream()
                        .map(TitleId::value)
                        .toList());

        List<String> externalTitleIds = userTitleSummary.stream()
                .map(TitleSummaryQueryResult::getTitleId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        Map<String, MediaSummaryQueryResult> mediaSummaryMap = catalogMediaQueries.getMediaSummaryMatching(externalTitleIds)
                .stream()
                .collect(Collectors.toMap(MediaSummaryQueryResult::getTitleId, t -> t));

        List<ListItemsDetailsView.Items> itemsDetails = userTitleSummary
                .stream()
                .map(title -> {
                    var media = mediaSummaryMap.get(title.getTitleId());
                    return new ListItemsDetailsView.Items(
                            title.getUserTitleId(),
                            media.getName(),
                            title.getTrackingStatus(),
                            title.getUserTitleRating(),
                            media.getTmdbRating(),
                            media.getReleaseDate()
                    );
                })
                .toList();


        return new ListItemsDetailsView(
                watchlist.id().value(),
                watchlist.getName(),
                watchlist.getUserTitleIdReferences().size(),
                itemsDetails
        );
    }

}
