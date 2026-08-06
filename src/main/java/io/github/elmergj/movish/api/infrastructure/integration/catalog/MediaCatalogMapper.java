package io.github.elmergj.movish.api.infrastructure.integration.catalog;

import io.github.elmergj.movish.api.application.catalog.MediaDetails;

public interface MediaCatalogMapper<I>{

    MediaDetails toCatalogResult(I providerMediaInput);
}
