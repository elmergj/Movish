---
title: Responsabilidades
description: Define los límites de responsabilidad de Movish App, Movish API y los servicios externos.
---

El backend se sitúa entre el cliente, los proveedores externos y la capa de datos. Mantener separadas estas responsabilidades facilita la evolución del sistema y mantiene el comportamiento específico de los proveedores fuera del cliente cuando es posible.

## Movish App

- Presenta la experiencia de usuario.
- Envía solicitudes a Movish API.
- Muestra los datos de la aplicación y las acciones del usuario.
- No es propietaria del modelo de persistencia del backend.

## Movish API

- Expone la API consumida por Movish App.
- Traduce las solicitudes HTTP a commands y queries de aplicación.
- Coordina el comportamiento de la aplicación y el acceso a datos.
- Aplica las reglas de negocio mediante entidades y servicios de dominio.
- Normaliza el contenido recibido de proveedores de catálogo externos.
- Resuelve usuarios autenticados y protege las operaciones correspondientes.
- Gestiona el acceso a los datos de aplicación respaldados por PostgreSQL.

El backend mantiene estas responsabilidades separadas en sus paquetes de aplicación, dominio, infraestructura e interfaz REST.

## Proveedores externos

- TMDB y OMDb proporcionan contenido de películas y series mediante adaptadores.
- El soporte de autenticación de Firebase valida la información de identidad cuando está configurado.

Estos proveedores permanecen en los límites externos. Sus APIs no deben definir el contrato público completo de Movish App ni el modelo de dominio interno.
