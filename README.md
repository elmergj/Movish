# Movish

Movish is a personal media-tracking platform for discovering, organizing, and following movies and TV shows. The backend provides the REST API behind the platform, combining a domain-oriented Java application with external media catalog providers and PostgreSQL persistence.

> **Development notice:** Movish is an active work in progress. Some features, architectural decisions, and implementation details have been added to support immediate development needs and may change as the project matures. The database configuration is also intended for the current development stage, not as a final production setup.

> **Status:** actively evolving. The API, domain model, and documentation are under development.

## What It Does

The Movish API is being built around the everyday workflow of managing a personal media library:

- Search for movies and TV shows through an external catalog provider.
- Add titles to a personal library and view their details.
- Mark titles as favorites and update their tracking status.
- Create and manage custom watchlists.
- Support user registration, profiles, and authenticated access.
- Keep provider integrations behind application and domain-level contracts.

The project is designed to support a future client application while keeping the backend independent from any particular user interface.

## Technology Stack

- Java 21
- Spring Boot 3.5
- Spring Web and Jakarta Validation
- Spring Data JPA
- Spring Security
- PostgreSQL
- Gradle Kotlin DSL
- MapStruct and Lombok

## Project Structure

The backend follows a domain-oriented structure under `src/main/java/io/github/elmergj/movish/api/`:

```text
api/
├── application/       # Use cases, commands, queries, and application services
├── config/             # Spring, security, cache, and client configuration
├── domain/             # Aggregates, value objects, domain services, events, and ports
├── infrastructure/     # Persistence and external provider integrations
└── interfaces/rest/    # REST controllers and HTTP response/request models
```

The main domain concepts currently include users, titles, media catalog entries, libraries, and watchlists. The architecture is evolving toward clear boundaries between domain rules, application orchestration, infrastructure adapters, and the HTTP interface.

## Requirements

Before running the API locally, install:

- JDK 21
- PostgreSQL
- Git

The repository includes the Gradle Wrapper, so a local Gradle installation is not required.

## Configuration

The application reads its database and catalog provider settings from environment variables. Set the following values before starting the API:

```bash
export POSTGRES_MOVISH_URL="jdbc:postgresql://localhost:5432/movish"
export POSTGRES_MOVISH_USER="your-database-user"
export POSTGRES_MOVISH_PASSWORD="your-database-password"
export PROV1_MOVISH_URL="https://your-provider.example/api"
export PROV1_MOVISH_K="your-provider-token"
```

Provider-specific setup may change as the integration layer evolves. Do not commit credentials to the repository.

## Getting Started

Clone the repository and start the application with the Gradle Wrapper:

```bash
git clone https://github.com/elmergj/Movish.git
cd Movish
./gradlew bootRun
```

On Windows, use `gradlew.bat bootRun` instead.

The application entry point is `io.github.elmergj.movish.api.MovishApplication`.

## Gradle Commands

```bash
./gradlew bootRun       # Start the API locally
./gradlew test          # Run the test suite
./gradlew build         # Compile, test, and package the application
./gradlew clean         # Remove generated build output
```

## API Areas

The current REST interface is organized by responsibility:

| Area | Base path | Purpose |
| --- | --- | --- |
| Catalog | `/search` | Search the external media catalog |
| Library | `/library` | Add, inspect, update, and remove library titles |
| Watchlists | `/list` | Create and manage custom lists |
| Users | `/registration`, `/account` | Registration and account operations |
| Administration | `/health`, `/authentication` | Health and provider status endpoints |

The API surface is still being implemented, so endpoint contracts may change while the domain and application layers mature.

## Documentation

Project documentation is available in [`docs/`](docs/), including architecture decisions, requirements, planning, and testing notes.

The Astro documentation site lives in [`docs/site/`](docs/site/). To run it locally:

```bash
cd docs/site
pnpm install
pnpm dev
```

The site is available at `http://localhost:4321/Movish/`.

## Development Notes

The default local JPA configuration currently uses `create-drop`, which recreates the schema during the application lifecycle. This is a temporary development choice made to support the current implementation. Treat it as development configuration and review it before deploying to a persistent or production environment.

The test suite includes Spring Boot smoke coverage and domain-focused tests for users, titles, and watchlists.

## License

The Movish backend is licensed under the GNU Affero General Public License v3.0.

The documentation site in `docs/site/` is a separate project adapted from the DocKit Astro template and retains its MIT license. See [`docs/site/LICENSE`](docs/site/LICENSE).

Third-party dependencies retain their respective licenses.
