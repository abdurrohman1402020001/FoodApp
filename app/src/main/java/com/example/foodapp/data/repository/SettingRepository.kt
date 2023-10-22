//package com.example.foodapp.data.repository
//
//import com.example.foodapp.data.local.datastore.SettingDataStoreManager
//import kotlinx.coroutines.flow.Flow
//
//
//class SettingRepository(private val settingDataStoreManager: SettingDataStoreManager) {
//    val getSetting: Flow<Boolean> = settingDataStoreManager.getSetting
//    suspend fun setSetting(condition: Boolean) = settingDataStoreManager.setSetting(condition)
//}