---
title: API Reference
description: Explore the endpoints and services provided by Movish API.
---

This section documents the HTTP contract, request formats, responses, and services exposed by Movish API in a Swagger/OpenAPI-inspired format.

Movish API is the backend contract consumed by Movish App. It normalizes external catalog data, coordinates user libraries and watchlists, and exposes responses prepared for the client.

The reference is based on the current Spring Boot controllers. It describes public HTTP behavior rather than every internal application or domain class. Because the project is still evolving, some contracts are explicitly marked as provisional.

## API Snapshot

```yaml
openapi: 3.0.3
info:
	title: Movish API
	version: development
servers:
	- url: http://localhost:8080
tags:
	- name: Catalog
	- name: Library
	- name: Watchlists
	- name: Users
	- name: Administration
```

There is not yet a generated OpenAPI document in the backend. This documentation is the current human-readable contract while the endpoint and security models continue to stabilize.

Start with [authentication](./authentication), then review [endpoints](./endpoints), [data models](./data-models), and [errors](./errors).