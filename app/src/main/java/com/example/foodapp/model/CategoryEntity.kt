package com.example.foodapp.model


import com.google.gson.annotations.SerializedName

data class CategoryEntity(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<DataCategory>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)