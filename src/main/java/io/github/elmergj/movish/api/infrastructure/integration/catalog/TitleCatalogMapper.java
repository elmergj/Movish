package io.github.elmergj.movish.api.infrastructure.integration.catalog;

import io.github.elmergj.movish.api.domain.model.entity.catalog.search.TitleDetailsResult;

public interface TitleCatalogMapper<I>{

    TitleDetailsResult toCatalogResult(I providerMediaInput);
}
