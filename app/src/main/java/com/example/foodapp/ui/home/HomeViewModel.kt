package com.example.foodapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.repository.SettingRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val settingRepository: SettingRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    val getSetting = settingRepository.getSetting.asLiveData()
    fun setSetting(condition: Boolean) = viewModelScope.launch {
        settingRepository.setSetting(condition)
    }
}