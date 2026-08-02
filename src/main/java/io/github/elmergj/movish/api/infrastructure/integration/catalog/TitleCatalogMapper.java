package io.github.elmergj.movish.api.infrastructure.integration.catalog;

import io.github.elmergj.movish.api.application.catalog.search.MediaDetailsResult;

public interface TitleCatalogMapper<I>{

    MediaDetailsResult toCatalogResult(I providerMediaInput);
}
