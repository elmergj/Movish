---
title: Flujo de datos
description: Explica cómo se mueven los datos por Movish y sus servicios externos.
---

A alto nivel, Movish sigue este flujo:

```text
Movish App
    |
    v
Interfaces REST
    |
    v
Casos de uso de aplicación
   / \
  v   v
Dominio  Adaptadores externos
  |          |
  v          v
PostgreSQL  Proveedores de catálogo / identidad
```

## Flujo de contenido

Para las funcionalidades de películas y series, un controller REST reenvía la solicitud a un servicio de aplicación. El servicio utiliza una fuente de catálogo o un adaptador de proveedor, transforma los datos externos en vistas de aplicación y devuelve un resultado normalizado a Movish App.

La aplicación cliente consume Movish API como su contrato orientado a la aplicación, en lugar de depender directamente de los formatos de respuesta de TMDB u otros proveedores.

## Flujo autenticado

Para las operaciones protegidas, Movish App envía información de autenticación con la solicitud. La integración de seguridad e identidad resuelve el usuario autenticado antes de que el servicio de aplicación continúe con la operación solicitada.

## Flujo de persistencia

Los datos que pertenecen a Movish se gestionan mediante el backend y se persisten en PostgreSQL. Los repositorios de dominio definen los contratos necesarios, mientras que la infraestructura proporciona las implementaciones concretas de base de datos.

Este flujo describe los límites principales y no cada llamada interna. Las operaciones individuales se implementan mediante commands, queries, comportamiento de dominio y puertos de repositorio.
