package com.example.mvvm.network



import com.example.mvvm.model.CardImageModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("albums/1/photos")
    fun fetchAllCardImage(): Call<List<CardImageModel>>

}