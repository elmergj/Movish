---
title: Arquitectura
description: Explora la arquitectura de Movish y cómo trabajan juntas sus partes principales.
---

Esta sección describe la estructura de Movish y la relación entre la aplicación cliente, la API, los servicios externos y la capa de datos.

Movish API es un backend Java 21 y Spring Boot construido con Gradle. Actúa como límite entre Movish App, los proveedores externos de catálogo y autenticación, y la persistencia en PostgreSQL.

El backend se organiza alrededor de casos de uso de aplicación, reglas de dominio, adaptadores de infraestructura e interfaces REST. Esta estructura mantiene el comportamiento de negocio independiente de HTTP, la persistencia y los detalles específicos de cada proveedor.

El proyecto sigue evolucionando. La arquitectura documentada aquí refleja el código fuente y la dirección de diseño actuales; los límites y las integraciones pueden cambiar a medida que madure la API.

Comienza con el [stack tecnológico](./technology-stack), y después revisa los [componentes del sistema](./system-components), las [responsabilidades](./responsibilities) y las [integraciones](./integrations).
