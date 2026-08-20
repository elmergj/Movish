---
title: Architecture
description: Explore the architecture of Movish and how its main parts work together.
---

This section describes the structure of Movish and the relationship between the application, API, external services, and data layer.

Movish API is a Java and Spring Boot backend that provides the services used by Movish App. It acts as the boundary between the mobile client, external content and authentication providers, and the persistence layer.

The current documentation focuses on responsibilities and system boundaries. Detailed implementation diagrams and internal package structures will be added after the backend architecture is documented from the source code.

Start with the [technology stack](./technology-stack), then review the [system components](./system-components), [responsibilities](./responsibilities), and [integrations](./integrations).