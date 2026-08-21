---
title: Data Flow
description: Explain how data moves through Movish and its external services.
---

At a high level, Movish follows this flow:

```text
Movish App
		|
		v
REST interfaces
		|
		v
Application use cases
	 / \
	v   v
Domain  External adapters
	|          |
	v          v
PostgreSQL  Catalog / identity providers
```

## Content Flow

For movie and TV show features, a REST controller forwards the request to an application service. The service uses a catalog source or provider adapter, maps external data into application views, and returns a normalized result to Movish App.

The mobile application therefore consumes Movish API as its application-facing contract instead of depending directly on TMDB response formats.

## Authenticated Flow

For protected operations, Movish App sends authentication information with the request. The security and identity integration resolves the authenticated user before the application service continues with the requested operation.

## Persistence Flow

Application data that belongs to Movish is managed through the backend and persisted in PostgreSQL. Domain repositories define the required persistence contracts while infrastructure provides the concrete database implementations.

This flow describes the main boundaries rather than every internal call. Individual operations are implemented through commands, queries, domain behavior, and repository ports.
