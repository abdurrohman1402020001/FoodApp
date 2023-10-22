package com.example.foodapp.Network

import com.example.foodapp.model.CategoryEntity
import com.example.foodapp.model.MenuEntity
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface RestfulApi {
    @GET("listmenu")
    fun getAllMenu(): Call<MenuEntity>
    @GET("category")
    fun getCategory(): Call<CategoryEntity>

}