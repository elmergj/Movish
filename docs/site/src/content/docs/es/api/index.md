---
title: Referencia de la API
description: Explora los endpoints y servicios proporcionados por Movish API.
---

Esta sección documenta el contrato HTTP, los formatos de solicitud, las respuestas y los servicios expuestos por Movish API con un formato inspirado en Swagger/OpenAPI.

Movish API es el contrato backend consumido por Movish App. Normaliza datos de catálogos externos, coordina bibliotecas y listas de usuario, y expone respuestas preparadas para el cliente.

La referencia se basa en los controllers actuales de Spring Boot. Describe el comportamiento HTTP público y no cada clase interna de aplicación o dominio. Como el proyecto sigue evolucionando, algunos contratos están marcados explícitamente como provisionales.

## Resumen de la API

```yaml
openapi: 3.0.3
info:
  title: Movish API
  version: development
servers:
  - url: http://localhost:8080
tags:
  - name: Catalog
  - name: Library
  - name: Watchlists
  - name: Users
  - name: Administration
```

Todavía no existe un documento OpenAPI generado en el backend. Esta documentación es el contrato legible actual mientras los modelos de endpoints y seguridad continúan estabilizándose.

Comienza con [autenticación](./authentication), y después revisa [endpoints](./endpoints), [modelos de datos](./data-models) y [errores](./errors).
