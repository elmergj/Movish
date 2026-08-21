---
title: Use Cases
description: Capture the main user and system use cases supported by Movish.
---

Movish API organizes user behavior through application commands, queries, and services. These use cases coordinate domain rules and repository ports without exposing infrastructure details to the client.

Initial use-case areas include:

- Searching catalog content through `SearchMediaQuery`.
- Viewing media details and normalized catalog data.
- Adding and removing titles from a personal library.
- Updating favorite and tracking status for library titles.
- Creating, renaming, and deleting watchlists.
- Adding and removing titles from watchlists.
- Registering users and updating account profiles.

## Application Flow

Commands represent state-changing operations, while queries represent reads. Application services coordinate these operations and return explicit success or failure outcomes to the REST layer.

Each use case should describe its actor, goal, request, expected result, relevant domain rules, and failure cases. Detailed endpoint examples can be added as the API contracts stabilize.