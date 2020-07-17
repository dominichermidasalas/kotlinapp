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

    fun register(phoneNumber : String){
        var token = (100..1000).random().toString()
        var user = initializeUser(phoneNumber,token)
        Log.i("async","a punto de enviar sms")
        sendSMS(phoneNumber)
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun sendSMS(phoneNumber: String){
        Log.i("async","dentro de enviar sms")
        var basic = Credentials.basic("AC3c2f400abbb0c6024f357deee75a3ce5", "d885a933425de52dcec71fddacd0cc64")
        SmsApi.retrofitService.postNumber(basic, phoneNumber, "+12183068958", "https://demo.twilio.com/owl.png", "Hola").enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i("async", "Error")
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.i("async POST", "response: " + response.body())
            }
        })
    }
}