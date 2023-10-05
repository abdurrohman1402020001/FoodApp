package com.example.foodapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodapp.data.local.database.dao.FoodDao
import com.example.foodapp.data.local.database.entity.FoodEntity
import com.example.foodapp.utils.Constant


@Database(entities = [FoodEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getInstance(ctx: Context): AppDatabase {
            return INSTANCE ?: synchronized(AppDatabase::class.java) {

                val instances = Room.databaseBuilder(
                    ctx.applicationContext, AppDatabase::class.java, Constant.DB_NAME
                ).build()
                instances
            }
        }
    }
}