package com.example.foodapp.data.repository

import androidx.room.Delete
import androidx.room.Query
import com.example.foodapp.data.local.database.dao.FoodDao
import com.example.foodapp.data.local.database.entity.FoodEntity


class FoodRepository(private val foodDao: FoodDao) {
    suspend fun addFood(foodEntity: FoodEntity) = foodDao.addFood(foodEntity)

    suspend fun removeFood(foodEntity: FoodEntity) = foodDao.removeFood(foodEntity)

    suspend fun getAllFoodCart(): List<FoodEntity> = foodDao.getAllFoodCart()

    suspend fun getTotalFood(): Int = foodDao.getTotalFood()

    suspend fun updateStock(stock: Int, id: Int, harga: Int) = foodDao.updateStock(stock, id, harga)

    suspend fun deleteAll() = foodDao.deleteAll()
}