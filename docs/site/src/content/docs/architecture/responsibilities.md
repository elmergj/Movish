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
- Translates HTTP requests into application commands and queries.
- Coordinates application behavior and data access.
- Applies business rules through domain entities and services.
- Normalizes content received from external catalog providers.
- Resolves authenticated users and enforces protected operations.
- Manages access to PostgreSQL-backed application data.

The backend keeps these responsibilities separated across its application, domain, infrastructure, and REST interface packages.

## External Providers

- TMDB and OMDb provide movie and TV show content through provider adapters.
- Firebase authentication support validates identity information when configured.

These providers remain external boundaries. Their APIs should not define the complete public contract of Movish App or the internal domain model.