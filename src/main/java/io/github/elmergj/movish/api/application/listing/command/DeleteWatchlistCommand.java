package io.github.elmergj.movish.api.application.listing.command;

public record DeleteWatchlistCommand(
        String userId,
        String customListId
){
}
