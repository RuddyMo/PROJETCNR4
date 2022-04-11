package com.example.projetcnr4.network

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET


interface DonneesService
{

    @GET("index.php")
    fun getDonnees(): Call<String>
}