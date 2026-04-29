package io.github.elmergj.movish.api.application.listing.query;

import io.github.elmergj.movish.api.application.catalog.query.TitleQueries;
import io.github.elmergj.movish.api.application.catalog.query.TitleSummaryQueryResult;
import io.github.elmergj.movish.api.application.library.query.UserTitleQueries;
import io.github.elmergj.movish.api.application.library.query.UserTitleSummaryQueryResult;
import io.github.elmergj.movish.api.domain.model.entity.library.UserTitleId;
import io.github.elmergj.movish.api.domain.model.entity.listing.TitleList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListingQueryService {

    private final UserTitleQueries userTitleQueries;
    private final TitleQueries titleQueries;

    public ListItemsDetailsView getListItemsDetails(TitleList titleList){

        List<UserTitleSummaryQueryResult> userTitleSummary =
                userTitleQueries.getUserTitleSummaryMatching(titleList.getUserTitleIdReferences()
                        .stream()
                        .map(UserTitleId::value)
                        .toList());

        List<String> externalTitleIds = userTitleSummary.stream()
                .map(UserTitleSummaryQueryResult::getTitleId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        Map<String, TitleSummaryQueryResult> titleSummaryMap = titleQueries.getTitleSummaryMatching(externalTitleIds)
                .stream()
                .collect(Collectors.toMap(TitleSummaryQueryResult::getTitleId, t -> t));

        List<ListItemsDetailsView.Items> itemsDetails = userTitleSummary
                .stream()
                .map(userTitle -> {
                    var title = titleSummaryMap.get(userTitle.getTitleId());
                    return new ListItemsDetailsView.Items(
                            userTitle.getUserTitleId(),
                            title.getName(),
                            userTitle.getTrackingStatus(),
                            userTitle.getUserTitleRating(),
                            title.getTmdbRating(),
                            title.getReleaseDate()
                    );
                })
                .toList();


        return new ListItemsDetailsView(
                titleList.id().value(),
                titleList.getName(),
                titleList.getUserTitleIdReferences().size(),
                itemsDetails
        );
    }

}
