package com.example.foodapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.local.database.entity.FoodEntity
import com.example.foodapp.data.repository.FoodRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val foodRepository: FoodRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    private val _number = MutableLiveData<Int>(0)
    val number: LiveData<Int> get() = _number

    fun increment() {
        _number.postValue(_number.value?.plus(1))
    }

    fun decrement() {
        if (_number.value!! > 0){
            _number.postValue(_number.value?.minus(1))
        }
    }

    fun addFood(foodEntity: FoodEntity) = viewModelScope.launch {
        foodRepository.addFood(foodEntity)
    }
}