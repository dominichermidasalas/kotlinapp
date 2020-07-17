package com.example.kotlin.admin

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.R
import com.example.kotlin.database.User
import com.example.kotlin.database.UserDao
import kotlinx.coroutines.*
import java.util.*

class AdminViewModel(val database: UserDao, application: Application) : AndroidViewModel(application) {
    var livedata = MutableLiveData<List<User>>()
    private var viewModelJob = Job()
    private val uiScope =  CoroutineScope(Dispatchers.Main + viewModelJob)
    init{
        Log.i("ALL_USERS", "entrando")
        loadAllUsers()
    }
    private fun loadAllUsers(){
        Log.i("ALL_USERS", "entrando a loadallusers")
        uiScope.launch {
            livedata.value = getAllUsers()
            Log.i("ALL_USERS", livedata.value.toString())
        }
    }
    private suspend fun getAllUsers(): List<User>? {
        return withContext(Dispatchers.IO){
            database.getAllUsers()
        }
    }

}