package io.github.elmergj.movish.api.domain.model.entity.user;

import io.github.elmergj.movish.api.domain.exception.ValidationException;
import io.github.elmergj.movish.api.domain.shared.Ensure;

import java.util.regex.Pattern;

public class Email {

    private final String value;

    private static final Pattern BASIC_EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String email) {
        Ensure.that(email, "Email").isNotBlank().maxLength(254);
        requireValidFormat(email);
        return new Email(email);
    }

    public String value() {
        return value;
    }

    private static void requireValidFormat(String email) {
        if (!BASIC_EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Invalid email format");
        }
    }
}
