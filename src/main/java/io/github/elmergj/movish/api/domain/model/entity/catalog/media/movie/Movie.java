package io.github.elmergj.movish.api.domain.model.entity.catalog.media.movie;

import io.github.elmergj.movish.api.domain.model.entity.catalog.media.MediaDetails;

public record Movie (
        String id,
        int durationMinutes
)
        implements MediaDetails {
}
