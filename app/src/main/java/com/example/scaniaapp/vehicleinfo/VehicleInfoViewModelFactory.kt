package com.example.scaniaapp.vehicleinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scaniaapp.database.FeedbackDao

class VehicleInfoViewModelFactory(
    private val dataSource: FeedbackDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleInfoViewModel::class.java)) {
            return VehicleInfoViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewHolder class")
    }
}