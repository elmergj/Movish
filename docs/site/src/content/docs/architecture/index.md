---
title: Architecture
description: Explore the architecture of Movish and how its main parts work together.
---

This section describes the structure of Movish and the relationship between the client application, API, external services, and data layer.

Movish API is a Java 21 and Spring Boot backend built with Gradle. It acts as the boundary between Movish App, external catalog and authentication providers, and PostgreSQL persistence.

The backend is organized around application use cases, domain rules, infrastructure adapters, and REST interfaces. This structure keeps business behavior independent from HTTP, persistence, and provider-specific details.

The project is still evolving. The architecture documented here reflects the current source code and design direction; boundaries and integrations may change as the API matures.

Start with the [technology stack](./technology-stack), then review the [system components](./system-components), [responsibilities](./responsibilities), and [integrations](./integrations).