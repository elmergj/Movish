---
title: Casos de uso
description: Documenta los principales casos de uso soportados por Movish.
---

Movish API organiza el comportamiento del usuario mediante commands, queries y servicios de aplicación. Estos casos de uso coordinan las reglas de dominio y los puertos de repositorio sin exponer detalles de infraestructura al cliente.

Las áreas actuales de casos de uso incluyen:

- Buscar contenido en el catálogo mediante `SearchMediaQuery`.
- Consultar detalles multimedia y datos de catálogo normalizados.
- Añadir y eliminar títulos de una biblioteca personal.
- Actualizar favoritos y el estado de seguimiento de títulos de la biblioteca.
- Crear, renombrar y eliminar listas de seguimiento.
- Añadir y eliminar títulos de listas de seguimiento.
- Registrar usuarios y actualizar perfiles de cuenta.

## Flujo de aplicación

Los commands representan operaciones que cambian el estado, mientras que las queries representan lecturas. Los servicios de aplicación coordinan estas operaciones y devuelven resultados explícitos de éxito o fallo a la capa REST.

Cada caso de uso debe describir su actor, objetivo, solicitud, resultado esperado, reglas de dominio relevantes y casos de fallo. Se pueden añadir ejemplos detallados de endpoints cuando los contratos de la API se estabilicen.
