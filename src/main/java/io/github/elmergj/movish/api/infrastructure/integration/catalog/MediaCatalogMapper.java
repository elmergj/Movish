package io.github.elmergj.movish.api.infrastructure.integration.catalog;

import io.github.elmergj.movish.api.application.catalog.search.MediaDetailsResult;

public interface MediaCatalogMapper<I>{

    MediaDetailsResult toCatalogResult(I providerMediaInput);
}
