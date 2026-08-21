---
title: Despliegue
description: Documenta cómo se configuran y publican los servicios de Movish.
---

La documentación de despliegue describe cómo se configura y publica el servicio Movish API en los distintos entornos.

## Consideraciones actuales

Movish todavía está en desarrollo, por lo que el flujo de despliegue y la configuración de infraestructura pueden cambiar.

Los temas principales incluyen:

- Runtime requerido y configuración de variables de entorno.
- Conectividad con PostgreSQL.
- Credenciales de Firebase y configuración del servidor.
- Credenciales de la API de TMDB y configuración de proveedores.
- Configuración del dominio y del acceso protegido.
- Build y publicación mediante Gradle.
- Health checks y verificación operativa.

La configuración JPA local utiliza actualmente `create-drop`, una decisión temporal para desarrollo. Debe revisarse antes de desplegar en un entorno persistente o de producción.

<!-- TODO: Añadir el flujo de despliegue cuando estén documentados el hosting y el dominio protegido. -->
