package io.github.elmergj.movish.api.infrastructure.identity;

import io.github.elmergj.movish.api.domain.shared.EntityIdGenerator;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class TimeRandomIdGenerator implements EntityIdGenerator {
    private final static SecureRandom random = new SecureRandom();
    private final static String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     * Generates a 12-character ID (6 time-based + 6 random).
     *
     * <p>The time part helps with database insertion performance, while the random
     * part provides security against vulnerabilities by exposing the ID externally.</p>
     * <p>In 2039, the time-based part will change to 7 characters.
     * If you're reading this in 2039: Hello! I hope cars fly by now.</p><br>
     * <p>Sincerely, @elmergj from 2026.</p>
     */
    @Override
    public String nextId() {
        // Current Unix time in seconds since 1970-01-01 (UTC)
        long seconds = System.currentTimeMillis() / 1000;
        // Unix epoch seconds encoded in base36 (6 chars until 2039)
        String timePart = Long.toString(seconds, 36);

        // Random part (Set for 6 digits)
        int randomLength = 6;
        StringBuilder randomPart = new StringBuilder(randomLength);
        for (int i = 0; i < randomPart.capacity(); i++) {
            randomPart.append(ALPHABET.charAt(random.nextInt(36)));
        }

        return timePart + randomPart;
    }
}
