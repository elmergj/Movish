---
title: Instalación
description: Instala Movish y prepara el proyecto para el desarrollo local.
---

Movish API es una aplicación Spring Boot basada en Gradle. El repositorio incluye el Gradle Wrapper, por lo que no es necesario instalar Gradle localmente.

## Requisitos

Instala las siguientes herramientas antes de comenzar:

- JDK 21
- PostgreSQL
- Git

## Clonar el repositorio

```bash
git clone https://github.com/elmergj/Movish.git
cd Movish
```

## Verificar el proyecto

Compila el proyecto y ejecuta sus pruebas con el Gradle Wrapper:

```bash
./gradlew test
```

En Windows, utiliza `gradlew.bat test`.

Cuando termine la verificación, continúa con [Configuración](./configuration/) antes de iniciar la API.

## Iniciar la API

```bash
./gradlew bootRun
```
