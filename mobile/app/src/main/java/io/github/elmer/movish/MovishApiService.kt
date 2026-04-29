package io.github.elmer.movish;

import retrofit2.http.GET

interface MovishApiService {
    @GET("health")
    suspend fun getHealth(): String
}