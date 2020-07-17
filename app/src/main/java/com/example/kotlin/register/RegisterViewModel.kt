package com.example.kotlin.register

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    enum class RegistrationState {
        ALREADY_REGISTERED,        // Initial state, the user needs to authenticate
        NOT_REGISTERED,        // The user has authenticated successfully
        INVALID_REGISTER  // Authentication failed
    }
    val registrationState = MutableLiveData<RegistrationState>()
    var phoneNumber: String

    init{
        registrationState.value = RegistrationState.INVALID_REGISTER
        phoneNumber = ""
    }

    fun register(phoneNumber: String):Boolean {
        if(phoneNumber == "3129430816") {
            Log.i("RegisterViewModel", "numero repetido")
            this.phoneNumber = phoneNumber
            registrationState.value = RegistrationState.ALREADY_REGISTERED
        } else if(phoneNumber == ""){
            Log.i("RegisterViewModel", "numero vacio")
            this.phoneNumber = phoneNumber
            registrationState.value = RegistrationState.INVALID_REGISTER
        } else {
            Log.i("RegisterViewModel", "numero valido")
            this.phoneNumber = phoneNumber
            registrationState.value = RegistrationState.NOT_REGISTERED
            return true
        }
        return false
    }
}