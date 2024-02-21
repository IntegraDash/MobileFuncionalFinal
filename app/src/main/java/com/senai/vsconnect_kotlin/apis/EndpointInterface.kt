package com.senai.vsconnect_kotlin.apis

import com.google.gson.JsonObject
import com.senai.vsconnect_kotlin.models.Login
import com.senai.vsconnect_kotlin.models.Alerta
import com.senai.vsconnect_kotlin.models.Erro
import com.senai.vsconnect_kotlin.models.Estrategia
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EndpointInterface {
    @GET("alertas")
    fun listarAlertas() : Call<List<Alerta>>

    @GET("erro")
    fun listarErros() : Call<List<Erro>>

    @GET("estrategias")
    fun listarEstrategias() : Call<List<Estrategia>>

    @POST("login")
    fun login(@Body usuario: Login) :Call<JsonObject>
}