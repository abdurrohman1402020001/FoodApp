//package com.example.foodapp.ui.cart
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.foodapp.data.local.database.entity.FoodEntity
//import com.example.foodapp.data.repository.FoodRepository
//import kotlinx.coroutines.launch
//
//class CartViewModel(private val foodRepository: FoodRepository) : ViewModel() {
//    // TODO: Implement the ViewModel
//    private val _food = MutableLiveData<List<FoodEntity>>()
//    val food: LiveData<List<FoodEntity>> get() = _food
//
//    private val _harga = MutableLiveData<Int>()
//    val harga: LiveData<Int> get() = _harga
//
//    fun getAllFood() = viewModelScope.launch {
//        _food.postValue(foodRepository.getAllFoodCart())
//    }
//
//    fun getTotalFood() = viewModelScope.launch {
//        _harga.postValue(foodRepository.getTotalFood())
//    }
//
//    fun deleteFood(foodEntity: FoodEntity) = viewModelScope.launch {
//        foodRepository.removeFood(foodEntity)
//    }
//
//    fun updateStock(stock: Int, id: Int, harga: Int) = viewModelScope.launch {
//        foodRepository.updateStock(stock, id, harga)
//    }
//
//}