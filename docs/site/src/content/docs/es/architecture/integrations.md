---
title: Integraciones
description: Explica los servicios externos conectados a Movish API.
---

## Proveedores de catálogo

Movish API utiliza adaptadores de proveedores de catálogo para obtener datos de películas y series. El código fuente incluye actualmente integraciones con The Movie Database y OMDb. El backend actúa como límite alrededor de esos proveedores: solicita contenido, transforma respuestas externas, aplica las necesidades de la aplicación y expone datos preparados para Movish App.

Consulta la [documentación de la API de TMDB](https://developer.themoviedb.org/docs) y sus [condiciones de uso](https://www.themoviedb.org/terms-of-use).

Las URLs y credenciales de los proveedores se proporcionan mediante variables de entorno. La configuración genérica actual utiliza `PROV1_MOVISH_URL` y `PROV1_MOVISH_K`, de modo que los detalles del proveedor pueden cambiar sin incluir credenciales en el código fuente.

## Firebase

El soporte de autenticación de Firebase permite a Movish API validar la información de identidad y conectar un usuario autenticado con las operaciones del backend. El repositorio también contiene componentes de autenticación falsa para escenarios locales o de prueba.

Consulta la [documentación de Firebase Authentication](https://firebase.google.com/docs/auth).

## Límites de integración

El código de integración vive en `infrastructure/integration`. Los adaptadores y mappers traducen los DTO específicos de cada proveedor a representaciones de aplicación o dominio, manteniendo los formatos externos fuera del modelo de negocio central.

El comportamiento ante fallos de proveedores, las decisiones de caché y la gestión de credenciales para producción todavía se están definiendo.
