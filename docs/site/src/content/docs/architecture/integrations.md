---
title: Integrations
description: Explain the external services connected to Movish API.
---

## Catalog Providers

Movish API uses catalog provider adapters to retrieve movie and TV show data. The source currently includes integrations for The Movie Database and OMDb. The backend acts as a boundary around those providers: it requests content, maps external responses, applies application needs, and exposes data prepared for Movish App.

See the [TMDB API documentation](https://developer.themoviedb.org/docs) and [TMDB API terms of use](https://www.themoviedb.org/terms-of-use).

Provider URLs and credentials are supplied through environment variables. The current generic configuration uses `PROV1_MOVISH_URL` and `PROV1_MOVISH_K` so provider details can change without moving credentials into source code.

## Firebase

Firebase authentication support allows Movish API to validate identity information and connect an authenticated user with backend operations. The repository also contains fake authentication components for local or test scenarios.

See the [Firebase Authentication documentation](https://firebase.google.com/docs/auth).

## Integration Boundaries

Integration code lives in `infrastructure/integration`. Adapters and mappers translate provider-specific DTOs into application or domain representations, keeping external response formats out of the core business model.

Provider failure behavior, caching decisions, and production credential management are still being refined.