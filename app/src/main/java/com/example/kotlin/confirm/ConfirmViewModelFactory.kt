package com.example.kotlin.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ConfirmViewModelFactory(private val phoneNumber: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConfirmViewModel::class.java)) {
            return ConfirmViewModel(phoneNumber) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}