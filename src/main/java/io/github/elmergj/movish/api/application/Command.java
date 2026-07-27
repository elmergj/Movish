package io.github.elmergj.movish.api.application;

/**
 * Represents the root contract for all application commands.
 *
 * <p>A {@code Command} identifies an object as an explicit request to perform
 * an operation that changes the state of the system or triggers a business
 * action.</p>
 *
 * <p>This interface is intentionally empty. Its purpose is not to define
 * behavior, but to provide a common type that allows the application layer to
 * enforce a clear boundary between commands and regular data transfer
 * objects.</p>
 *
 * <p>Commands should represent an intention rather than a generic data
 * container. They describe what operation is being requested and contain the
 * information required for its execution.</p>
 *
 * <p>Example:</p>
 *
 * <pre>{@code
 * public record CreateUserCommand(
 *         String username,
 *         String email
 * ) implements Command {
 * }
 * }</pre>
 *
 * <p>Commands may be executed through a dedicated command handler:</p>
 *
 * <pre>{@code
 * public interface CommandHandler<C extends Command, R extends CommandResult> {
 *
 *     R handle(C command);
 * }
 * }</pre>
 *
 * <p>However, implementing a dedicated handler class is not mandatory. For
 * simple application flows, commands may be consumed directly by application
 * services or methods that execute the corresponding use case:</p>
 *
 * <pre>{@code
 * public UserCreatedOutcome createUser(CreateUserCommand command) {
 *     // execute use case logic
 * }
 * }</pre>
 *
 * <p>This contract helps maintain a consistent application architecture by
 * preventing arbitrary DTOs from being used as commands and allowing tools,
 * tests, and conventions to identify valid command objects.</p>
 *
 * <p>The command abstraction defines the input contract of a use case, while
 * the execution strategy remains flexible depending on the complexity and
 * requirements of the operation.</p>
 */
public interface Command {
}