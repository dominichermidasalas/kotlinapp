package com.example.kotlin.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.database.UserDao
import java.lang.IllegalArgumentException

class RegisterViewModelFactory(
    private val dataSource: UserDao,
    private val application: Application ) : ViewModelProvider.Factory {

    @Suppress("uncheked_cast")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(dataSource, application) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}