---
title: Configuration
description: Configure Movish for your local environment.
---

Movish API reads the database and external catalog settings from environment variables. Configure these values in your shell before starting the application.

## Required variables

```bash
export POSTGRES_MOVISH_URL="jdbc:postgresql://localhost:5432/movish"
export POSTGRES_MOVISH_USER="your-database-user"
export POSTGRES_MOVISH_PASSWORD="your-database-password"
export PROV1_MOVISH_URL="https://your-provider.example/api"
export PROV1_MOVISH_K="your-provider-token"
```

The PostgreSQL database must exist and be reachable using the configured connection values. The `PROV1_MOVISH_*` variables provide the URL and token for the configured catalog provider.

## Development database behavior

The current local JPA configuration uses `create-drop`. The schema is recreated during the application lifecycle, which is convenient during development but destructive for persistent data.

Do not use this default configuration as-is for production. Review the JPA and database settings before deploying the application to a persistent environment.

Keep sensitive values out of the documentation and source control.
