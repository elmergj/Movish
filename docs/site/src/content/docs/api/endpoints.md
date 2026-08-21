---
title: Endpoints
description: Document the endpoints exposed by Movish API.
---

This page is the endpoint reference for the current Movish API snapshot. The local server uses `http://localhost:8080` as its base URL and does not currently define a version prefix.

Each endpoint should document:

- HTTP method and path.
- Authentication requirements.
- Query parameters, path parameters, and request body.
- Successful response shape.
- Known error responses.
- A short example request and response.

The endpoint list below is based on the current Spring Boot controllers. Some operations and response contracts are still provisional.

## Administration

### `GET /health`

Returns a plain-text health response.

```bash
curl http://localhost:8080/health
```

**Response `200 OK`**

```text
Hi!, All good from Movish Server
```

### `GET /authentication`

Forwards an authentication-status request to the configured provider client.

> **Provisional:** the route is present, but its provider contract and authentication behavior are still under development.

## Catalog

### `GET /search`

Searches the external media catalog.

| Query parameter | Type | Required | Description |
| --- | --- | --- | --- |
| `query` | string | no | Text used for the catalog search. |
| `page` | integer | no | Requested result page. |
| `size` | integer | no | Requested page size. |

```bash
curl "http://localhost:8080/search?query=matrix&page=0&size=20"
```

> **In progress:** the controller currently invokes the catalog service but returns `null` instead of the assembled response. The final response schema is not yet published.

## Library

All library operations receive the current authenticated user from Spring Security.

### `POST /library/title`

Adds a media item to the user's library.

```json
{
	"mediaId": "external-media-id",
	"mediaType": "MOVIE"
}
```

**Response `201 Created`** returns a `TitleCreationResponse` and a `Location` header. A duplicate title currently returns `400 Bad Request`.

### `GET /library/{titleId}`

Returns the details of a library title.

### `PUT /library/title/{titleId}/favorite`

Updates the favorite flag.

```json
{
	"favorite": true
}
```

### `PUT /library/title/{titleId}/tracking`

Updates the tracking status.

```json
{
	"trackingStatus": "WATCHING"
}
```

### `DELETE /library/title/{titleId}`

Removes a title from the user's library.

## Watchlists

### `POST /list`

Creates a custom watchlist.

```json
{
	"name": "Weekend watchlist"
}
```

Returns `201 Created` with the created list summary.

### `GET /list/{listId}`

Returns a watchlist overview with its identifier, name, and total item count.

### `POST /list/{listId}/titles`

Adds a library title to a watchlist.

```json
{
	"titleId": "title-id"
}
```

### `GET /list/{listId}/titles`

Returns the watchlist and its title items.

### `PUT /list/{listId}/name`

Renames a custom watchlist.

```json
{
	"newName": "Classics"
}
```

### `DELETE /list/{listId}/title/{titleId}`

Removes a title from a watchlist.

### `DELETE /list/{listId}`

Deletes a watchlist.

## Users

### `POST /registration`

Registers the current authenticated identity in Movish.

```json
{
	"email": "user@example.com",
	"name": "Movish User"
}
```

Returns `201 Created` with the registered user's basic data.

### `GET /account/profile`

Returns the current user's profile.

### `PUT /account/profile`

Updates the current user's profile.

```json
{
	"username": "movish-user",
	"name": "Movish User",
	"email": "user@example.com"
}
```
