---
title: Errores
description: Documenta las respuestas de error y la solución de problemas de Movish API.
---

Esta página describe el comportamiento de errores actualmente visible en Movish API.

## Respuestas conocidas

| Estado | Significado | Ejemplos actuales |
| --- | --- | --- |
| `200 OK` | La operación se completó correctamente. | Lecturas y actualizaciones. |
| `201 Created` | Se creó un recurso. | Registro, título de biblioteca y creación de listas. |
| `400 Bad Request` | La solicitud o el estado solicitado no es válido. | Título duplicado en la biblioteca o actualización de estado repetida. |
| `401 Unauthorized` | La autenticación falta o no es válida. | Previsto para los flujos protegidos de producción. |
| `404 Not Found` | El recurso solicitado no existe. | Fallos de búsqueda de los servicios de dominio. |
| `500 Internal Server Error` | Fallo inesperado del servidor o del proveedor. | Error no gestionado de la aplicación o integración. |

## Forma actual de los errores

Todavía no existe un envelope de error único implementado en todos los controllers. Algunos fallos devuelven actualmente un mensaje en texto plano, por ejemplo:

```text
The title with media id external-media-id already exists
```

Por tanto, los cuerpos de respuesta de validación y excepciones pueden variar mientras se consolida el contrato de la API.

## Recomendaciones para clientes

- Trata cualquier respuesta que no sea 2xx como una operación fallida.
- No dependas de mensajes en texto plano como identificadores estables.
- Gestiona por separado los fallos de validación, autenticación, recursos inexistentes, proveedores e imprevistos.
- Da prioridad al estado HTTP y al contexto de la operación hasta que se publique un esquema de errores versionado.
