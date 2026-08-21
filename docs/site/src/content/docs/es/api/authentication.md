---
title: Autenticación
description: Documenta la autenticación y autorización de Movish API.
---

Movish API está diseñada para utilizar Firebase Authentication como proveedor de identidad externo. El repositorio contiene un `FirebaseUserAuthenticator`, pero su implementación actual es provisional.

## Comportamiento actual de desarrollo

En los perfiles actuales `dev` y `default`:

- `FakeAuthFilter` proporciona un principal de autenticación de prueba.
- Spring Security permite todas las solicitudes.
- La protección CSRF está desactivada para las pruebas locales de la API.
- `/registration` y `/health` se gestionan como rutas especiales sin autenticación mediante el filtro falso.

Esta configuración está destinada al desarrollo y las pruebas. No debe considerarse una configuración de autenticación para producción.

## Flujo previsto de solicitud

El flujo de producción previsto es:

1. El cliente se autentica con el proveedor de identidad configurado.
2. El cliente envía el token del proveedor con las solicitudes a la API.
3. Movish API valida el token mediante la integración de autenticación.
4. La identidad autenticada se resuelve en un usuario interno de Movish.
5. Las operaciones protegidas utilizan esa identidad interna para comprobar la propiedad de los recursos.

El formato exacto de las cabeceras HTTP, el ciclo de vida del token, las rutas públicas, las rutas protegidas y las respuestas de error todavía no están finalizados. No asumas todavía un contrato `Bearer` estable.

Consulta la [documentación de Firebase Authentication](https://firebase.google.com/docs/auth) para conocer la referencia del proveedor.
