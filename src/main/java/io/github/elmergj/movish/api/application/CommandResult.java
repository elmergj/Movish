package io.github.elmergj.movish.api.application;

/**
 * Represents the root contract for all command execution results.
 *
 * <p>A {@code CommandResult} identifies an object as a valid output of a
 * command execution. It acts as a common boundary between application
 * commands and their consumers, allowing different command result strategies
 * while maintaining a consistent architectural contract.</p>
 *
 * <p>Not every command requires the same level of result complexity. Some
 * commands have a single successful outcome, while others may have multiple
 * expected business outcomes, such as successful execution or a known business
 * failure.</p>
 *
 * <p>Simple command results can implement this interface directly:</p>
 *
 * <pre>{@code
 * public record UserCreatedOutcome(
 *         UUID userId,
 *         String username
 * ) implements CommandResult {
 * }
 * }</pre>
 *
 * <p>For commands where business failures are part of the expected flow,
 * specialized result abstractions can extend this contract:</p>
 *
 * <pre>{@code
 * public interface Result<S, F>
 *         extends CommandResult {
 * }
 *
 * Result<SharingCompleted, SharingFailure> result;
 * }</pre>
 *
 * <p>This design allows command handlers to expose a clear and explicit
 * contract without forcing every command into the same result structure.
 * The command itself determines the appropriate level of complexity.</p>
 *
 * <p>Examples:</p>
 *
 * <ul>
 *     <li>
 *         A simple command:
 *         <pre>{@code
 *         CreateUserCommand
 *             -> UserCreatedOutcome
 *         }</pre>
 *     </li>
 *
 *     <li>
 *         A command with expected business alternatives:
 *         <pre>{@code
 *         ShareTitleListCommand
 *             -> SuccessResult<ShareCompleted>
 *             -> FailureResult<TargetUseryNotFound>
 *         }</pre>
 *     </li>
 * </ul>
 *
 * <p>This interface intentionally contains no methods. Its purpose is to
 * provide a shared type and preserve architectural consistency, not to define
 * execution behavior.</p>
 */
public interface CommandResult {
}