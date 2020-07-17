package com.example.kotlin.confirm

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.database.User
import com.example.kotlin.database.UserDao
import kotlinx.coroutines.*
import java.util.*


class ConfirmViewModel(phoneNumber: String,
                       val database: UserDao, application: Application) :  AndroidViewModel(application) {

    var phoneNumberUser : String = phoneNumber
    private var viewModelJob = Job()
    private val uiScope =  CoroutineScope(Dispatchers.Main + viewModelJob)
    private var _validate = MutableLiveData<Boolean>()


    val validate : LiveData<Boolean>
        get() = _validate

    override fun onCleared() {
        super.onCleared()
         viewModelJob.cancel()
    }


    public fun validate(token : String){
        Log.i("ViewModel","this is phone number from confirmViewModel $phoneNumberUser and token $token")
        uiScope.launch {
            if (getUserToken(phoneNumberUser) == token){
                validateUser(phoneNumberUser)
                _validate.value = true
            }
            else{
                _validate.value = false
            }
        }
    }

    private suspend fun getUserToken(phoneNumber: String): String {
        return withContext(Dispatchers.IO){
            var user = database.get(phoneNumber)
            Log.i("ViewModel","Esto fue lo que obtuve de la BD ${user.token}")
            user.token
        }
    }

    private suspend fun validateUser(phoneNumber: String){
        return withContext(Dispatchers.IO){
            var user = database.get(phoneNumber)
            user.validated = true
            database.update(user)
        }
    }

}