package io.github.elmergj.movish.api.application.listing.command;

public record CreateWatchlistCommand(
        String userId,
        String name
){
}
