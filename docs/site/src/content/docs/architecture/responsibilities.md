---
title: Responsibilities
description: Define the responsibility boundaries of Movish App, Movish API, and external services.
---

The backend exists between the mobile client, external providers, and the data layer. Keeping these responsibilities separated makes the system easier to evolve and keeps provider-specific behavior away from the client where possible.

## Movish App

- Presents the user experience.
- Sends requests to Movish API.
- Renders application data and user actions.
- Does not own the backend persistence model.

## Movish API

- Exposes the API consumed by Movish App.
- Coordinates application behavior and data access.
- Wraps and normalizes content received from TMDB.
- Validates authentication information supplied by the client.
- Manages access to PostgreSQL-backed application data.

## External Providers

- TMDB provides movie and TV show content.
- Firebase provides authentication services.

These providers remain external boundaries. Their APIs should not define the complete public contract of Movish App.