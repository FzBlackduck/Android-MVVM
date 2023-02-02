package com.example.mvvm.network


import com.example.mvvm.model.CardImageModel

import retrofit2.Call

class ServiceManager {

    private fun instance(): ApiInterface {
        return BaseService.create()
    }

    fun getCardImage(): Call<List<CardImageModel>>{
        return instance().fetchAllCardImage()
    }

}