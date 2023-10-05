package com.example.foodapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val image: Int,
    val name: String,
    val price: Int,
    val description: String,
    val location: String
) : Parcelable
