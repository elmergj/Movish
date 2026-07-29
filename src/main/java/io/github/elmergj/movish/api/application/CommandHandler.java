package io.github.elmergj.movish.api.application;

/**
 * Defines a handler responsible for executing a command.
 *
 * <p>A command handler receives a command and produces a result that belongs
 * to the {@link CommandResult} family.</p>
 *
 * <p>This abstraction is intended for cases where command execution requires
 * a dedicated component, such as complex workflows, batch processes, or
 * operations with their own execution lifecycle.</p>
 *
 * @param <C> the type of command handled
 * @param <R> the type of result produced by the handler
 */
public interface CommandHandler<C, R extends CommandResult> {

    /**
     * Executes the given command.
     *
     * @param command the command to execute
     * @return the result produced by the command execution
     */
    R handle(C command);
}