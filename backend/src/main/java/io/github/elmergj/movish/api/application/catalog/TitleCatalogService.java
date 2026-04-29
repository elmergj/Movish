package io.github.elmergj.movish.api.application.catalog;

import io.github.elmergj.movish.api.application.catalog.command.SearchTitleCommand;
import io.github.elmergj.movish.api.domain.model.entity.catalog.TitleCatalogSource;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.SearchResultSet;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.TitleDetailsResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.search.TitleSummaryResult;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.MediaType;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.Title;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.TitleId;
import io.github.elmergj.movish.api.domain.model.entity.catalog.title.TitleRating;
import io.github.elmergj.movish.api.domain.repository.TitleRepository;
import io.github.elmergj.movish.api.domain.shared.EntityIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TitleCatalogService {

    private final TitleCatalogSource titleCatalogSource;
    private final TitleRepository titleRepository;
    private final EntityIdGenerator entityIdGenerator;

    public SearchResultSet<TitleSummaryResult> searchTitleByQuery(SearchTitleCommand command) {

        return titleCatalogSource.searchTitleByQuery(command.query(), command.page(),
               command.pageSize());

    }

    private TitleDetailsResult fetchTitleDetails(String externalTitleId, MediaType mediaType) {
        return titleCatalogSource.fetchTitleDetails(externalTitleId, mediaType);
    }


    public Title getTitleDetails(String externalTitleId, MediaType mediaType) {

        return titleRepository.findByExternalIdAndMediaType(externalTitleId, mediaType)
                .orElseGet(() -> {
                    TitleDetailsResult titleDetails = fetchTitleDetails(externalTitleId, mediaType);
                    Title title = new Title(
                            entityIdGenerator.generate(TitleId::from),
                            titleDetails.externalTitleId(),
                            titleDetails.name(),
                            titleDetails.releaseDate(),
                            TitleRating.of(titleDetails.tmdbTitleRating()),
                            titleDetails.mediaType()
                            );
                    saveTitleToCatalog(title);
                    return title;
                });
    }

    private void saveTitleToCatalog(Title title) {
        titleRepository.save(title);
    }
}