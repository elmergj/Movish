---
title: First Steps
description: Complete the first steps after installing and configuring Movish.
---

Once the environment variables are configured, start the API from the repository root:

```bash
./gradlew bootRun
```

## Check the API

The application exposes a health endpoint:

```bash
curl http://localhost:8080/health
```

A successful response confirms that the Spring Boot application is running.

## Continue developing

Run the test suite whenever you change the domain or application code:

```bash
./gradlew test
```

Next, explore the [API documentation](../api/) and the [architecture documentation](../architecture/) to understand the available endpoints and the responsibilities of each application layer.

Remember that Movish is still under active development. Endpoint contracts, provider integrations, and domain decisions may change as the project evolves.
