package com.example.gatitos.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gatitos.data.Repositorio
import com.example.gatitos.data.models.EnvioVoto
import com.example.gatitos.data.models.GatosItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(val context: Context) : ViewModel() {

    private val repositorio = Repositorio(context)
    val gatosLiveData = MutableLiveData<List<GatosItem>?>()
    val selectedGato = MutableLiveData<GatosItem>()
    val votosLiveData = MutableLiveData<List<EnvioVoto>?>()

    fun obtenerListaVotos(userId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repositorio.obtenerListaVotos(userId)
            var listaVotos = response.body()
        }
    }

//  fun votar(imageId: String, userId: String, valor: Int) {
//      val envioVoto = EnvioVoto(imageId, userId, valor)

//      CoroutineScope(Dispatchers.IO).launch {
//          val response = repositorio.votar(envioVoto)
//          val respuestaVotes = response.body()
//          val mensaje = respuestaVotes?.message
//      }
//  }

    fun getGato() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repositorio.getBreeds()
            if (response.isSuccessful) {
                val miRespuesta = response.body()
                gatosLiveData.postValue(miRespuesta)
            }
        }
    }

    class MyViewModeFactory(private val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Context::class.java).newInstance(context)
        }
    }
}