package com.example.kotlin.register

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kotlin.database.User
import com.example.kotlin.database.UserDao
import com.example.kotlin.network.SmsApi
import kotlinx.coroutines.*
import okhttp3.Credentials
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class RegisterViewModel(val database: UserDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    public var allReadyvalidated = MutableLiveData<Boolean>()
    public var observar  = true

    fun register(phoneNumber : String){
        Log.i("raro", "otra vez aquí")
        userAlreadyValidate(phoneNumber)
    }

    fun create(phoneNumber : String){
        var token = (100000..999999).random().toString()
        var user = initializeUser(phoneNumber,token)
        Log.i("async","a punto de enviar sms")
        sendSMS(phoneNumber,token)
        return user
    }

    private val uiScope =  CoroutineScope(Dispatchers.Main + viewModelJob)

    public var user = MutableLiveData<User>()


    private fun initializeUser(phoneNumber: String, token: String){
        Log.i("async","initializeUser $phoneNumber token $token")
        uiScope.launch {
            user.value = getUserFromDatabase(phoneNumber, token)
        }
    }

    private fun userAlreadyValidate(phoneNumber: String){
        uiScope.launch {
            allReadyvalidated.value = getUserFromDatabase(phoneNumber)
        }
    }

    private suspend fun getUserFromDatabase(phoneNumber: String, token: String): User? {
        return withContext(Dispatchers.IO){
            var user = database.get(phoneNumber)
            Log.i("async","getUserFromDatabase $user")

            if (user == null){
                user = User(phoneNumber = phoneNumber, token = token)
                database.insert(user)
            }else{
                user.token = token
                database.update(user)
            }
            user
        }
    }

    private suspend fun getUserFromDatabase(phoneNumber: String): Boolean{
        return withContext(Dispatchers.IO){
            var user = database.get(phoneNumber)
            Log.i("ultimo","esto es lo que regresó databse $user")
            if(user == null){
               false
            }else{
                user.validated
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun sendSMS(phoneNumber: String, token: String){
        Log.i("async","dentro de enviar sms")
        var basic = Credentials.basic("XXX", "XXX")
        SmsApi.retrofitService.postNumber(basic, phoneNumber, "+12183068958", "https://demo.twilio.com/owl.png", token).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("async", t.toString())
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("async POST", "response: " + response.body())
            }
        })
    }
}