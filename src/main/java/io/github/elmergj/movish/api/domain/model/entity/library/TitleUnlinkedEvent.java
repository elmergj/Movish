package io.github.elmergj.movish.api.domain.model.entity.library;

import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.domain.shared.Event;

public record TitleUnlinkedEvent(
        TitleId titleId,
        UserId userId)
        implements Event {
}
