---
title: Componentes del sistema
description: Describe los componentes principales que forman Movish.
---

Movish se organiza alrededor de una aplicación cliente, una API backend, proveedores externos y una capa de persistencia.

## Movish App

Movish App es la aplicación cliente utilizada para descubrir, organizar y seguir películas y series. Consume Movish API en lugar de comunicarse directamente con la base de datos del backend.

## Movish API

Movish API es el servicio backend que expone el contrato de la aplicación. Recibe solicitudes de Movish App, coordina commands y queries, aplica el comportamiento del dominio, gestiona la persistencia y devuelve datos preparados para el cliente.

Internamente, el backend se divide en cuatro áreas principales:

- `application/`: commands, queries, servicios de aplicación y orquestación de casos de uso.
- `domain/`: agregados, entidades, value objects, servicios de dominio, eventos y puertos de repositorio.
- `infrastructure/`: adaptadores de proveedores, resolución de identidad, implementaciones de persistencia y mapeos externos.
- `interfaces/rest/`: controllers HTTP, modelos de solicitud, modelos de respuesta y assemblers.

## Proveedores externos

El código fuente contiene actualmente integraciones para:

- [The Movie Database (TMDB)](https://developer.themoviedb.org/docs): datos de películas y series.
- OMDb: un adaptador adicional para proveedores de catálogo.
- [Firebase](https://firebase.google.com/docs): autenticación y validación de tokens.

## Capa de persistencia

PostgreSQL almacena los datos de la aplicación gestionados por Movish API mediante Spring Data JPA. Los principales conceptos persistidos actualmente son usuarios, títulos y listas de seguimiento.
