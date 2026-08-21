---
title: Primeros pasos
description: Completa los primeros pasos después de instalar y configurar Movish.
---

Una vez configuradas las variables de entorno, inicia la API desde la raíz del repositorio:

```bash
./gradlew bootRun
```

## Comprobar la API

La aplicación expone un endpoint de estado:

```bash
curl http://localhost:8080/health
```

Una respuesta correcta confirma que la aplicación Spring Boot está funcionando.

## Continuar el desarrollo

Ejecuta la suite de pruebas cada vez que cambies el código de dominio o aplicación:

```bash
./gradlew test
```

Después, explora la [documentación de la API](../api/) y la [documentación de arquitectura](../architecture/) para comprender los endpoints disponibles y las responsabilidades de cada capa.

Recuerda que Movish todavía está en desarrollo activo. Los contratos de endpoints, las integraciones con proveedores y las decisiones de dominio pueden cambiar a medida que evoluciona el proyecto.
