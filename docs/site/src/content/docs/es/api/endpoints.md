---
title: Endpoints
description: Documenta los endpoints expuestos por Movish API.
---

Esta página es la referencia de endpoints de la versión actual de Movish API. El servidor local utiliza `http://localhost:8080` como URL base y actualmente no define un prefijo de versión.

Cada endpoint debe documentar:

- Método HTTP y ruta.
- Requisitos de autenticación.
- Parámetros de consulta, parámetros de ruta y cuerpo de la solicitud.
- Forma de la respuesta exitosa.
- Respuestas de error conocidas.
- Un ejemplo breve de solicitud y respuesta.

La lista de endpoints se basa en los controllers actuales de Spring Boot. Algunas operaciones y contratos de respuesta todavía son provisionales.

## Administración

### `GET /health`

Devuelve una respuesta de estado en texto plano.

```bash
curl http://localhost:8080/health
```

**Respuesta `200 OK`**

```text
Hi!, All good from Movish Server
```

### `GET /authentication`

Reenvía una solicitud de estado de autenticación al cliente del proveedor configurado.

> **Provisional:** la ruta existe, pero su contrato con el proveedor y su comportamiento de autenticación todavía están en desarrollo.

## Catálogo

### `GET /search`

Busca contenido en el catálogo externo.

| Parámetro de consulta | Tipo | Obligatorio | Descripción |
| --- | --- | --- | --- |
| `query` | string | no | Texto utilizado para la búsqueda en el catálogo. |
| `page` | integer | no | Página de resultados solicitada. |
| `size` | integer | no | Tamaño de página solicitado. |

```bash
curl "http://localhost:8080/search?query=matrix&page=0&size=20"
```

> **En desarrollo:** el controller invoca actualmente el servicio de catálogo, pero devuelve `null` en lugar de la respuesta ensamblada. El esquema final de respuesta todavía no está publicado.

## Biblioteca

Todas las operaciones de biblioteca reciben el usuario autenticado actual desde Spring Security.

### `POST /library/title`

Añade un elemento multimedia a la biblioteca del usuario.

```json
{
	"mediaId": "external-media-id",
	"mediaType": "MOVIE"
}
```

**Respuesta `201 Created`** devuelve un `TitleCreationResponse` y una cabecera `Location`. Un título duplicado devuelve actualmente `400 Bad Request`.

### `GET /library/{titleId}`

Devuelve los detalles de un título de la biblioteca.

### `PUT /library/title/{titleId}/favorite`

Actualiza el indicador de favorito.

```json
{
	"favorite": true
}
```

### `PUT /library/title/{titleId}/tracking`

Actualiza el estado de seguimiento.

```json
{
	"trackingStatus": "WATCHING"
}
```

### `DELETE /library/title/{titleId}`

Elimina un título de la biblioteca del usuario.

## Listas de seguimiento

### `POST /list`

Crea una lista de seguimiento personalizada.

```json
{
	"name": "Lista del fin de semana"
}
```

Devuelve `201 Created` con el resumen de la lista creada.

### `GET /list/{listId}`

Devuelve un resumen de la lista con su identificador, nombre y cantidad total de elementos.

### `POST /list/{listId}/titles`

Añade un título de la biblioteca a una lista.

```json
{
	"titleId": "title-id"
}
```

### `GET /list/{listId}/titles`

Devuelve la lista y sus títulos.

### `PUT /list/{listId}/name`

Cambia el nombre de una lista personalizada.

```json
{
	"newName": "Clásicos"
}
```

### `DELETE /list/{listId}/title/{titleId}`

Elimina un título de una lista.

### `DELETE /list/{listId}`

Elimina una lista de seguimiento.

## Usuarios

### `POST /registration`

Registra la identidad autenticada actual en Movish.

```json
{
	"email": "user@example.com",
	"name": "Usuario Movish"
}
```

Devuelve `201 Created` con los datos básicos del usuario registrado.

### `GET /account/profile`

Devuelve el perfil del usuario actual.

### `PUT /account/profile`

Actualiza el perfil del usuario actual.

```json
{
	"username": "movish-user",
	"name": "Usuario Movish",
	"email": "user@example.com"
}
```
