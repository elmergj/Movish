---
title: Authentication
description: Document authentication and authorization for Movish API.
---

Movish API is designed to use Firebase authentication as an external identity provider. The repository contains a `FirebaseUserAuthenticator`, but its current implementation is provisional.

## Current Development Behavior

In the current `dev` and `default` profiles:

- `FakeAuthFilter` supplies a test authentication principal.
- Spring Security permits all requests.
- CSRF protection is disabled for local API testing.
- `/registration` and `/health` are handled as special unauthenticated paths by the fake filter.

This setup is intended for development and testing. It must not be treated as production authentication.

## Intended Request Flow

The intended production flow is:

1. The client authenticates with the configured identity provider.
2. The client sends the provider token with API requests.
3. Movish API validates the token through the authentication integration.
4. The authenticated identity is resolved to an internal Movish user.
5. Protected application operations use that internal identity for ownership checks.

The exact HTTP header format, token lifecycle, public routes, protected routes, and failure responses are not finalized in the current implementation. Do not assume a stable `Bearer` contract yet.

See the [Firebase Authentication documentation](https://firebase.google.com/docs/auth) for the provider reference.
