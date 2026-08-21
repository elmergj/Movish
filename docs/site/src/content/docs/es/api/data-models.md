---
title: Modelos de datos
description: Documenta los modelos de datos utilizados por Movish API.
---

Esta página documenta las principales estructuras de datos públicas expuestas actualmente por Movish API.

Los modelos de respuesta de la API pueden diferir de los modelos de respuesta de proveedores externos porque Movish API normaliza y prepara los datos para Movish App. Los contratos públicos de respuesta se documentan por separado de las entidades internas de persistencia.

## Modelos de biblioteca

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

### Respuestas de actualización de estado

`TitleFavoriteStatusResponse` contiene `titleId` e `isFavorite`. `UpdateTitleTrackingStatusResponse` contiene `titleId` y `trackingStatus`.

## Modelos de listas

`WatchlistOverviewResponse` contiene `listId`, `name` y `totalElements`.

`WatchlistDetailsResponse` contiene `id`, `listName`, `totalItems` y un arreglo `items`. Cada elemento contiene `titleId`, `trackingStatus`, `dateAdded` y `mediaType`.

## Modelos de usuario

`UserProfileResponse` contiene `email`, `name`, `username` y `userAvatarId`.

`UserRegisteredResponse` contiene el `email` y el `name` del usuario registrado.

Estos esquemas representan los records de respuesta actuales en Java. La validación de campos, los formatos de fecha, los valores de enumeraciones y un envelope consistente todavía se están estandarizando.
