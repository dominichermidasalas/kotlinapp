package com.example.kotlin.confirm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConfirmViewModel : ViewModel() {

    private var _validate = MutableLiveData<Boolean>()
    val validate : LiveData<Boolean>
        get() = _validate
    init {
        Log.i("ViewModel", "Confirm view model")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "Oncleared")
    }


    public fun validate(phoneNumber : String, token : String){
        Log.i("ViewModel","this is phone number from confirmViewModel $phoneNumber and token $token")
        // TODO realmente igualalarlo al resultado que de la busqueda en la base de datos
        _validate.value = true
    }
}