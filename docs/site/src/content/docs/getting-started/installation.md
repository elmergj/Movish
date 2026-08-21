---
title: Installation
description: Install Movish and prepare the project for local development.
---

Movish API is a Gradle-based Spring Boot application. The repository includes the Gradle Wrapper, so a local Gradle installation is not required.

## Requirements

Install the following tools before starting:

- JDK 21
- PostgreSQL
- Git

## Clone the repository

```bash
git clone https://github.com/elmergj/Movish.git
cd Movish
```

## Verify the project

Compile the project and run its tests with the Gradle Wrapper:

```bash
./gradlew test
```

On Windows, use `gradlew.bat test` instead.

After the verification finishes, continue to [Configuration](./configuration/) before starting the API.

## Start the API

```bash
./gradlew bootRun
```
