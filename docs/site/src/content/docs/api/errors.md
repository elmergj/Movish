---
title: Errors
description: Document error responses and troubleshooting information for Movish API.
---

This page describes the error behavior currently visible in Movish API.

## Known Responses

| Status | Meaning | Current examples |
| --- | --- | --- |
| `200 OK` | Operation completed successfully. | Reads and updates. |
| `201 Created` | A resource was created. | Registration, library title, and watchlist creation. |
| `400 Bad Request` | The request or requested state is invalid. | Duplicate library title or repeated status update. |
| `401 Unauthorized` | Authentication is missing or invalid. | Intended for protected production flows. |
| `404 Not Found` | The requested resource does not exist. | Domain service lookup failures. |
| `500 Internal Server Error` | Unexpected server or provider failure. | Unhandled application or integration error. |

## Current Error Shape

There is not yet a single error envelope implemented across all controllers. Some failures currently return a plain-text message, for example:

```text
The title with media id external-media-id already exists
```

Validation and exception response bodies may therefore vary while the API contract is being consolidated.

## Client Guidance

- Treat any non-2xx response as a failed operation.
- Do not depend on plain-text messages as stable identifiers.
- Handle validation, authentication, missing-resource, provider, and unexpected failures separately.
- Prefer the HTTP status and operation context until a versioned error schema is published.

<!-- TODO: Add the confirmed error contract and troubleshooting guidance. -->
