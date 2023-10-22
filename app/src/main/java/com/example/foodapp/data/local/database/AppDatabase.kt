package com.example.foodapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodapp.data.local.database.dao.FoodDao
import com.example.foodapp.data.local.database.entity.FoodEntity



@Database(entities = [FoodEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
        companion object {
            private var INSTANCE: AppDatabase? = null
            fun getInstance(context: Context): AppDatabase? {
                if (INSTANCE == null) {
                    synchronized(AppDatabase::class) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, "AppDatabase.db"
                        ).fallbackToDestructiveMigration().build()
                    }
                }
                return INSTANCE
            }


            fun destroyInstance() {
                INSTANCE = null
            }

        }

}