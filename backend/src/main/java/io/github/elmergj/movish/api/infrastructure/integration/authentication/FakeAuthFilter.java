package io.github.elmergj.movish.api.infrastructure.integration.authentication;

import io.github.elmergj.movish.api.domain.model.entity.user.UserId;
import io.github.elmergj.movish.api.infrastructure.identity.InternalIdentityResolverByAuthIdProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
@Profile("dev") //Test: Test only
public class FakeAuthFilter extends OncePerRequestFilter {

    private final TestUserHolder testUserHolder;
    private final InternalIdentityResolverByAuthIdProvider internalIdResolver;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain chain)
            throws ServletException, IOException {

        String excludeRegistrationPath = "/registration";
        String excludeHealthPath = "/health";
        String path = request.getServletPath();

        String authId = testUserHolder.getCurrentAuthId();

        UsernamePasswordAuthenticationToken auth;

        if (path.equals(excludeHealthPath) || path.equals(excludeRegistrationPath)) {
            auth = new UsernamePasswordAuthenticationToken(
                    authId,
                    null,
                    Collections.emptyList()
            );
        } else {

            UserId userInternalId = internalIdResolver.resolveInternalId(authId);

            auth = new UsernamePasswordAuthenticationToken(
                    userInternalId.value(),
                    null,
                    Collections.emptyList()
            );
        }

        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }
}