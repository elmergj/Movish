---
title: Authentication
description: Document authentication and authorization for Movish API.
---

Movish API uses Firebase as its authentication provider. Requests from Movish App can include Firebase authentication information, which the backend validates before allowing protected operations.

The exact header format, token lifecycle, public routes, protected routes, and failure responses should be documented here after they are confirmed in the backend implementation.

See the [Firebase Authentication documentation](https://firebase.google.com/docs/auth) for the provider reference.
