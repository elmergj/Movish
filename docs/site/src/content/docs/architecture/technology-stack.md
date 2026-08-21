---
title: Technology Stack
description: Summarize the main technologies used by Movish API.
---

Movish API is built with a focused backend stack:

| Technology | Role |
| --- | --- |
| Java 21 | Backend programming language and runtime target. |
| Spring Boot 3.5 | Application framework for the API and backend services. |
| Spring Web | REST controllers and HTTP API support. |
| Spring Data JPA | Persistence abstraction for the relational data model. |
| Spring Security | Authentication and request security support. |
| Gradle Kotlin DSL | Build automation and dependency management. |
| PostgreSQL | Relational database and persistence layer. |
| MapStruct | Mapping between application and integration models. |
| Lombok | Boilerplate reduction in Java classes. |
| TMDB / OMDb adapters | External movie and TV catalog integrations. |
| Firebase integration | Authentication and token validation support. |

Local development requires JDK 21 and PostgreSQL. See [Getting Started](../getting-started/) for installation and environment configuration.

## Useful References

- [Java Documentation](https://docs.oracle.com/en/java/)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/documentation.html)
- [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Firebase Documentation](https://firebase.google.com/docs)
- [The Movie Database API Documentation](https://developer.themoviedb.org/docs)