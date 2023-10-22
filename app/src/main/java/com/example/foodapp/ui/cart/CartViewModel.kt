package com.example.foodapp.ui.cart

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.Network.RetrofitClient
import com.example.foodapp.data.local.database.AppDatabase
import com.example.foodapp.data.local.database.entity.FoodEntity
import com.example.foodapp.model.MenuEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class CartViewModel(app: Application) : AndroidViewModel(app) {
    // TODO: Implement the ViewModel
    private val dataFood : MutableLiveData<List<FoodEntity>> = MutableLiveData()


    fun getFoodObserver(): MutableLiveData<List<FoodEntity>> {
        return dataFood
    }
    suspend fun getAllData() {
        dataFood.postValue(AppDatabase.getInstance(getApplication())!!.foodDao().getAllFoodCart())
    }

    fun deleteFood(roomEntity: FoodEntity) = GlobalScope.launch {
        val foodDB = AppDatabase.getInstance(getApplication())!!.foodDao()
        foodDB.removeFood(roomEntity)
    }
    fun updateCount(stock: Int, id:Int, harga:Int){
        GlobalScope.launch {
            val user = AppDatabase.getInstance(getApplication())!!.foodDao()
            user.updateStock(stock, id, harga)
        }
    }


    fun insertData(data: FoodEntity){
        GlobalScope.async {
            val user = AppDatabase.getInstance(getApplication())!!.foodDao()
            user.addFood(data)
        }

    }


}