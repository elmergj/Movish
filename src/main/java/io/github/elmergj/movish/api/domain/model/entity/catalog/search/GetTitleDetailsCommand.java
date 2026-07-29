package io.github.elmergj.movish.api.domain.model.entity.catalog.search;

public record GetTitleDetailsCommand(
        String mediaExternalId,
        String mediaType
) {
}
