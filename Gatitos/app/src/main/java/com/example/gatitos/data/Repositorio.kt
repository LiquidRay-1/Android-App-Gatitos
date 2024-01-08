package com.example.gatitos.data

import android.content.Context
import com.example.gatitos.data.models.EnvioVoto
import com.example.gatitos.data.network.RetrofitHelper

class Repositorio(val context: Context) {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getBreeds() = retrofit.getAllBreeds()

    suspend fun obtenerListaVotos(userId: String) = retrofit.obtenerListaVotos(userId)
    //suspend fun votar(voto: EnvioVoto) = retrofit.votar(voto)

}