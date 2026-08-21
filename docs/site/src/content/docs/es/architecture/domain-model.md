---
title: Modelo de dominio
description: Describe los conceptos de negocio y las reglas principales del dominio de Movish.
---

El modelo de dominio de Movish describe los conceptos y reglas de negocio utilizados por el backend, independientemente de los formatos de respuesta de los proveedores externos.

## Conceptos principales

- **User:** posee los datos multimedia personales y define el límite de identidad para las acciones del usuario.
- **Title:** representa un título multimedia añadido a la biblioteca de un usuario. Contiene el comportamiento de seguimiento, favoritos, puntuación y reseñas.
- **Media:** representa el contenido del catálogo y su tipo, incluidas películas y series con temporadas y episodios.
- **Watchlist:** representa una lista de títulos propiedad de un usuario, con su propia identidad y ciclo de vida.

## Bloques de construcción del dominio

La capa de dominio contiene:

- Entidades y agregados como `Title`, `User` y `Watchlist`.
- Value objects como `TitleId`, `MediaId`, `UserId`, `Email` y `TrackingStatus`.
- Servicios de dominio para comportamientos entre entidades, incluidos los cambios de estado de seguimiento y puntuación.
- Eventos de dominio para cambios relevantes como actualizaciones de favoritos y eliminación de títulos.
- Interfaces de repositorio para títulos, usuarios y listas de seguimiento.

Los datos de catálogos externos se traducen en el límite de infraestructura en lugar de formar parte del modelo de dominio. Esto mantiene los DTO de los proveedores y los formatos específicos de las APIs fuera de las reglas de negocio.

## Dirección actual

El modelo actual está centrado en un agregado `Title` para la biblioteca de un usuario. Las primeras notas de diseño consideraban un modelo centrado en colecciones; la implementación avanza hacia un lenguaje más claro centrado en títulos y un comportamiento de agregados más específico.

Este modelo todavía está en desarrollo y puede evolucionar a medida que se validen más casos de uso y restricciones de persistencia.
