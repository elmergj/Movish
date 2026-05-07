# General Planning document 

This document contains general information about the movish-server project.

## Description
Movish-server consists of a backend project to be run on the server that provides the logic part of the [movish-platform]().
Its main function is to 
model the data provided by 
the platform [The Movie Database (TMDB)](https://www.themoviedb.org/) so that movish users can track, list, and discover new series and movies.
[The Movie Database (TMDB)](https://www.themoviedb.org/) is the main content provider for this platform. The data is obtained through its public API [The Movie Database API Documentation](https://developer.themoviedb.org/docs/getting-started) and used under the terms of use described in [The Movie Database API Terms of Use](https://www.themoviedb.org/api-terms-of-use).

## Objectives
* To provide a REST API. 
  service that allows management of [movish-platform]() userEntity data
* To provide a userEntity 
  registration and authentication validation service for movish-clients.

## Scope
The scope of movis-server is limited to providing the following services:
* Registration validation and userEntity authentication through [Firebase Auth API](https://firebase.google.com/docs/auth/admin)
* Modeling data obtained 
  from [The Movie Database 
  API Documentation](https://developer.themoviedb.org/docs/getting-started) so that it makes sense in the context of this platform, see [Platform Features](), that the Movish platform aims to provide.
* Provide the REST API of the already modeled data for customers who need to consume it and provide a graphical interface layer for users.

## Milestones
* Movish-server REST API
* Integration with [The Movie Database
  API](https://developer.themoviedb.org/docs/getting-started)
* Integration with [Firebase Auth API](https://firebase.google.com/docs/auth/admin)

To view information about the technical decisions made during the project, review the [docs/adr/](../adr)directory.

To view the list of the management and design tools used to carry out this project, review [Project Tools](Project-Tools.md) 

