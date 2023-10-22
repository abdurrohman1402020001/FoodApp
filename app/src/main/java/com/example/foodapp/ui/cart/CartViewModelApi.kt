package com.example.foodapp.ui.cart

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.Network.RetrofitClient
import com.example.foodapp.model.Order
import com.example.foodapp.model.OrderEntity
import retrofit2.Callback
import retrofit2.Response

class CartViewModelApi(): ViewModel() {
    lateinit var dataOrder : MutableLiveData<List<Order>>
    init {
        dataOrder = MutableLiveData()
    }
    fun postOrder(catatan:String, harga:Int, nama:String, jumlah:Int) {
        RetrofitClient.instance.postOrder(catatan, harga, nama, jumlah)
            .enqueue(object : Callback<OrderEntity> {
                override fun onResponse(call: retrofit2.Call<OrderEntity>, response: Response<OrderEntity>) {
                    if(response.isSuccessful){
                        val userResponse = response.body()
                        dataOrder.postValue(userResponse!!.orders)
                    }else{
                        Log.d("info", response.body().toString())
                    }
                }

                override fun onFailure(call: retrofit2.Call<OrderEntity>, t: Throwable) {
                    Log.d("Failed", t.message.toString())
                }
            })


    }
}