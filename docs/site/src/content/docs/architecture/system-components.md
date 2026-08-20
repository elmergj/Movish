---
title: System Components
description: Describe the main components that make up Movish.
---

Movish is organized around a mobile application, a backend API, external providers, and a persistence layer.

## Movish App

Movish App is the client application used to discover, organize, and follow movies and TV shows. It consumes Movish API rather than communicating directly with the backend data store.

## Movish API

Movish API is the backend service that exposes the application contract. It receives requests from Movish App, applies the required business behavior, coordinates external integrations, and returns data in a shape suitable for the client.

## External Providers

The backend currently integrates with:

- [The Movie Database (TMDB)](https://developer.themoviedb.org/docs): content and catalog data.
- [Firebase](https://firebase.google.com/docs): authentication services and token validation.

## Persistence Layer

PostgreSQL stores the application data managed by Movish API. The exact entities and relationships will be documented in the [domain model](./domain-model) once the backend model is reviewed in detail.
