---
title: Domain Model
description: Reserve a place for the Movish domain model and its business concepts.
---

The Movish domain model describes the business concepts and rules used by the backend independently of external provider response formats.

## Main Concepts

- **User:** owns personal media data and provides the identity boundary for user actions.
- **Title:** represents a media title added to a user's library. It contains tracking, favorite, rating, and review behavior.
- **Media:** represents catalog content and its type, including movies and TV shows with seasons and episodes.
- **Watchlist:** represents a user-owned list of titles with its own identity and lifecycle.

## Domain Building Blocks

The domain layer contains:

- Entities and aggregates such as `Title`, `User`, and `Watchlist`.
- Value objects such as `TitleId`, `MediaId`, `UserId`, `Email`, and `TrackingStatus`.
- Domain services for cross-entity behavior, including tracking status and rating changes.
- Domain events for meaningful state changes such as favorite updates and title removal.
- Repository interfaces for titles, users, and watchlists.

External catalog data is translated at the infrastructure boundary instead of becoming part of the domain model. This keeps provider DTOs and API-specific formats out of business rules.

## Current Direction

The current model is centered on a `Title` aggregate for a user's library. Earlier design notes considered a collection-centered model; the implementation is moving toward clearer title-centric language and more focused aggregate behavior.

This model is still under development and may evolve as more use cases and persistence constraints are validated.