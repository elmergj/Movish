---
title: Integrations
description: Explain the external services connected to Movish API.
---

## The Movie Database

Movish API consumes The Movie Database API as a content provider. The backend acts as a wrapper around that provider: it requests content, applies the application's response shape and filtering needs, and exposes data prepared for Movish App.

See the [TMDB API documentation](https://developer.themoviedb.org/docs) and [TMDB API terms of use](https://www.themoviedb.org/terms-of-use).

## Firebase

Firebase provides authentication services for the platform. Movish API uses Firebase authentication data to validate requests and connect an authenticated user with backend operations.

See the [Firebase Authentication documentation](https://firebase.google.com/docs/auth).

## Integration Boundaries

The detailed request flows, failure behavior, caching decisions, and provider-specific mappings will be documented after the corresponding backend code and configuration are reviewed.