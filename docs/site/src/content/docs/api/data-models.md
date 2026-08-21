---
title: Data Models
description: Document the data models used by Movish API.
---

This page documents the main public data structures currently exposed by Movish API.

The API response models may differ from external provider response models because Movish API normalizes and prepares provider data for Movish App. Public response contracts are documented separately from internal persistence entities.

## Library Models

### `TitleCreationResponse`

```json
{
	"titleId": "title-id",
	"mediaId": "external-media-id",
	"titleName": "The Matrix",
	"dateAdded": "2026-08-20T12:00:00Z"
}
```

### `TitleDetailsResponse`

```json
{
	"titleId": "title-id",
	"trackingStatus": "WATCHING",
	"dateAdded": "2026-08-20T12:00:00Z",
	"isFavorite": true,
	"titleDetailsContent": {
		"mediaId": "external-media-id",
		"name": "The Matrix",
		"releaseDate": "1999-03-31"
	}
}
```

### Status update responses

`TitleFavoriteStatusResponse` contains `titleId` and `isFavorite`. `UpdateTitleTrackingStatusResponse` contains `titleId` and `trackingStatus`.

## Watchlist Models

`WatchlistOverviewResponse` contains `listId`, `name`, and `totalElements`.

`WatchlistDetailsResponse` contains `id`, `listName`, `totalItems`, and an `items` array. Each item contains `titleId`, `trackingStatus`, `dateAdded`, and `mediaType`.

## User Models

`UserProfileResponse` contains `email`, `name`, `username`, and `userAvatarId`.

`UserRegisteredResponse` contains the registered user's `email` and `name`.

These schemas represent the current Java response records. Field validation, date formats, enum values, and a consistent envelope are still being standardized.
