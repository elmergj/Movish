package io.github.elmergj.movish.api.application.listing.command;

public record DeleteCustomListCommand(
        String userId,
        String customListId
){
}
