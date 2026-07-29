package io.github.elmergj.movish.api.infrastructure.identity;

import io.github.elmergj.movish.api.domain.shared.EntityIdGenerator;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * Generates compact, time-ordered identifiers composed of:
 *
 * <pre>
 * [current time in milliseconds, encoded in base36]
 * [10 random base36 characters]
 * </pre>
 *
 * <p>Example:
 *
 * <pre>
 * mec1i0f3k8n4x9q2v7
 * </pre>
 *
 * <p>The timestamp portion provides approximate chronological ordering,
 * while the random portion greatly reduces the probability of collisions.
 *
 * <p>Characteristics:
 * <ul>
 *   <li>Independent of any database sequence or auto-increment column.</li>
 *   <li>Roughly sortable by creation time.</li>
 *   <li>Uses a cryptographically strong random source ({@link SecureRandom}).</li>
 *   <li>Produces shorter identifiers than UUID while remaining highly unique.</li>
 * </ul>
 *
 * <p>This generator is suitable for application-level entity identifiers in
 * systems where globally unique, human-readable, and time-ordered IDs are
 * preferred over database-generated numeric identifiers.
 */
@Component
public class TimeRandomIdGenerator implements EntityIdGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int RANDOM_LENGTH = 10;

    /**
     * Generates a new identifier consisting of:
     *
     * <ol>
     *   <li>The current Unix timestamp in milliseconds encoded in base36.</li>
     *   <li>A 10-character random suffix using base36 characters
     *       ({@code 0-9a-z}).</li>
     * </ol>
     *
     * @return a new time-ordered identifier
     */
    @Override
    public String nextId() {
        // Current Unix time in milliseconds since 1970-01-01 (UTC)
        long millis = System.currentTimeMillis();

        // Unix epoch milliseconds encoded in base36
        String timePart = Long.toString(millis, 36);

        StringBuilder randomPart = new StringBuilder(RANDOM_LENGTH);
        for (int i = 0; i < RANDOM_LENGTH; i++) {
            randomPart.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return timePart + randomPart;
    }
}