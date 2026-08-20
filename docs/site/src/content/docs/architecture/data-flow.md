---
title: Data Flow
description: Explain how data moves through Movish and its external services.
---

At a high level, Movish follows this flow:

```text
Movish App
		|
		v
Movish API
	/     \
 v       v
TMDB   Firebase
	|
	v
PostgreSQL and application data
```

## Content Flow

For movie and TV show features, Movish API requests content from TMDB, applies the application's response shape and filtering needs, and returns a normalized result to Movish App.

The mobile application therefore consumes Movish API as its application-facing contract instead of depending directly on TMDB response formats.

## Authenticated Flow

For protected operations, Movish App sends authentication information with the request. Movish API validates that information through Firebase before continuing with the requested operation.

## Persistence Flow

Application data that belongs to Movish is managed through the backend and persisted in PostgreSQL. The exact persistence operations and domain relationships will be documented with the [domain model](./domain-model) after the backend implementation is reviewed.

This page intentionally describes boundaries rather than internal call chains. Detailed sequence diagrams can be added when the relevant use cases are documented.
