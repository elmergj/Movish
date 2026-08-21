---
title: System Components
description: Describe the main components that make up Movish.
---

Movish is organized around a client application, a backend API, external providers, and a persistence layer.

## Movish App

Movish App is the client application used to discover, organize, and follow movies and TV shows. It consumes Movish API rather than communicating directly with the backend data store.

## Movish API

Movish API is the backend service that exposes the application contract. It receives requests from Movish App, coordinates commands and queries, applies domain behavior, manages persistence, and returns data shaped for the client.

Internally, the backend is divided into four main areas:

- `application/`: commands, queries, application services, and use-case orchestration.
- `domain/`: aggregates, entities, value objects, domain services, events, and repository ports.
- `infrastructure/`: provider adapters, identity resolution, persistence implementations, and external mappings.
- `interfaces/rest/`: HTTP controllers, request models, response models, and assemblers.

## External Providers

The source currently contains integration code for:

- [The Movie Database (TMDB)](https://developer.themoviedb.org/docs): movie and TV catalog data.
- OMDb: an additional catalog provider adapter.
- [Firebase](https://firebase.google.com/docs): authentication and token validation support.

## Persistence Layer

PostgreSQL stores the application data managed by Movish API through Spring Data JPA. The main application concepts persisted by the backend are users, titles, and watchlists.
