package com.example.foodapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.foodapp.Network.RetrofitClient
import com.example.foodapp.model.CategoryEntity
import com.example.foodapp.model.DataCategory
import com.example.foodapp.model.DataMenu
import com.example.foodapp.model.MenuEntity


import retrofit2.Callback

import retrofit2.Response


class HomeViewModel : ViewModel(){
    lateinit var liveDataMenuMenu: MutableLiveData<List<DataMenu>>
    lateinit var liveDataCategory: MutableLiveData<List<DataCategory>>


    init {
        liveDataMenuMenu = MutableLiveData()

        liveDataCategory = MutableLiveData()
    }
    fun getMenu() {
        RetrofitClient.instance.getAllMenu()
        .enqueue(object : Callback<MenuEntity> {
            override fun onResponse(call: retrofit2.Call<MenuEntity>, response: Response<MenuEntity>) {
                if(response.isSuccessful){
                    val userResponse = response.body()
                    liveDataMenuMenu.postValue(userResponse!!.data)
                }else{
                    Log.d("info", response.body().toString())
                }
            }

            override fun onFailure(call: retrofit2.Call<MenuEntity>, t: Throwable) {
                Log.d("Failed", t.message.toString())
            }
        })


    }
    fun getCategories(){
        RetrofitClient.instance.getCategory()
            .enqueue(object : Callback<CategoryEntity> {
                override fun onResponse(call: retrofit2.Call<CategoryEntity>, response: Response<CategoryEntity>) {
                    if(response.isSuccessful){
                        val userResponse = response.body()
                        liveDataCategory.postValue(userResponse!!.data)
                    }else{
                        Log.d("info", response.body().toString())
                    }
                }

                override fun onFailure(call: retrofit2.Call<CategoryEntity>, t: Throwable) {
                    Log.d("Failed", t.message.toString())
                }
            })
    }
}