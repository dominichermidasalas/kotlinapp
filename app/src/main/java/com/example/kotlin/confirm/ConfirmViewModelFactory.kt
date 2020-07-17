package com.example.kotlin.confirm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.database.UserDao

class ConfirmViewModelFactory(private val phoneNumber: String,
                              private val dataSource: UserDao,
                              private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConfirmViewModel::class.java)) {
            return ConfirmViewModel(phoneNumber, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}