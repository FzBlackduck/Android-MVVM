package com.example.mvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.CardImageModel
import com.example.mvvm.network.ServiceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private val service = ServiceManager()

    var selectedItem : List<CardImageModel> = emptyList()

    private val _listCardImage = MutableLiveData<List<CardImageModel>?>()
    val listCardImage: LiveData<List<CardImageModel>?>
        get() = _listCardImage

    fun fetchAllPosts() {
        service.getCardImage().enqueue(object : Callback<List<CardImageModel>> {
            override fun onResponse(
                call: Call<List<CardImageModel>>,
                response: Response<List<CardImageModel>>
            ) {
                val res = response.body()
                if (response.code() == 200 && res != null) {
                    _listCardImage.value = res
                } else {
                    _listCardImage.value = null
                }
            }

            override fun onFailure(call: Call<List<CardImageModel>>, t: Throwable) {
                _listCardImage.value = null
            }
        })
    }

    fun getSelectedItem(cardImageModel: CardImageModel) {
        selectedItem = listOf(cardImageModel)
    }
}