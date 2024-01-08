package com.example.gatitos.data.network

import com.example.gatitos.data.models.EnvioGetVoto
import com.example.gatitos.data.models.GatosItem
import com.example.gatitos.data.models.RespuestaVotes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @Headers("x-api-key: live_kHnmPhCHFwfjWsUixwSLOFzeNZSdg25yg8z640MTRi1kZuge32H9aCDWNlRi7MFw")
    @GET("breeds")
    suspend fun getAllBreeds() : Response<List<GatosItem>>

    @Headers("x-api-key: live_kHnmPhCHFwfjWsUixwSLOFzeNZSdg25yg8z640MTRi1kZuge32H9aCDWNlRi7MFw")
    @GET("votes")
    suspend fun obtenerListaVotos(
        @Query("sub_id") userId: String
    ): Response<List<EnvioGetVoto>>

    @POST("votes")
    suspend fun votar(
        @Body voto: EnvioGetVoto
    ): Response<RespuestaVotes>
}