package io.github.elmergj.movish.api.application.library.query;

public sealed interface TitleQuery {

    record TitleDetailsQuery(
            String userId,
            String titleId
    ) implements TitleQuery {
    }
}
