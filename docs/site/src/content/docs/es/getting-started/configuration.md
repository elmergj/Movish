---
title: Configuración
description: Configura Movish para tu entorno local.
---

Movish API lee la configuración de la base de datos y del catálogo externo mediante variables de entorno. Configura estos valores en tu shell antes de iniciar la aplicación.

## Variables requeridas

```bash
export POSTGRES_MOVISH_URL="jdbc:postgresql://localhost:5432/movish"
export POSTGRES_MOVISH_USER="your-database-user"
export POSTGRES_MOVISH_PASSWORD="your-database-password"
export PROV1_MOVISH_URL="https://your-provider.example/api"
export PROV1_MOVISH_K="your-provider-token"
```

La base de datos PostgreSQL debe existir y ser accesible con los valores de conexión configurados. Las variables `PROV1_MOVISH_*` proporcionan la URL y el token del proveedor de catálogo configurado.

## Comportamiento de la base de datos de desarrollo

La configuración JPA local actual utiliza `create-drop`. El esquema se recrea durante el ciclo de vida de la aplicación, lo que resulta práctico durante el desarrollo, pero destruye los datos persistentes.

No utilices esta configuración por defecto tal cual en producción. Revisa la configuración JPA y de base de datos antes de desplegar la aplicación en un entorno persistente.

Mantén los valores sensibles fuera de la documentación y del control de versiones.
