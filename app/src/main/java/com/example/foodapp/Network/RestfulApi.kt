package com.example.foodapp.Network

import com.example.foodapp.model.CategoryEntity
import com.example.foodapp.model.MenuEntity
import com.example.foodapp.model.OrderEntity
import retrofit2.Call
import retrofit2.http.Body

import retrofit2.http.GET
import retrofit2.http.POST

interface RestfulApi {
    @GET("listmenu")
    fun getAllMenu(): Call<MenuEntity>
    @GET("category")
    fun getCategory(): Call<CategoryEntity>

    @POST("category")
    fun postOrder(@Body catatan: String,
                  @Body harga:Int,
                  @Body nama:String,
                  @Body jumlah:Int):Call<OrderEntity>

}