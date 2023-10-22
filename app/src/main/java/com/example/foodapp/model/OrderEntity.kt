package com.example.foodapp.model


import com.google.gson.annotations.SerializedName

data class OrderEntity(
    @SerializedName("orders")
    val orders: List<Order>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("username")
    val username: String
)