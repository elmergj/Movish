package io.github.elmergj.movish.api.application.catalog.search;

public record GetMediaDetailsCommand(
        String mediaExternalId,
        String mediaType
) {
}
