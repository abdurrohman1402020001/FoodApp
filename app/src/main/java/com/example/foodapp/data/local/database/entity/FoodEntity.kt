package com.example.foodapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodEntity(
    @PrimaryKey
    val id: Int? = null,
    val image: Int,
    val name: String,
    val price: Int,
    val description: String,
    val location: String,
    var stock: Int,
    val harga: Int
)
