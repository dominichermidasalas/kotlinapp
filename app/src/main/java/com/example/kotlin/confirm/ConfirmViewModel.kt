package com.example.kotlin.confirm

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class ConfirmViewModel(phoneNumber: String) : ViewModel() {

    var phoneNumberUser : String = phoneNumber

    private var _validate = MutableLiveData<Boolean>()
    val validate : LiveData<Boolean>
        get() = _validate

    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "Oncleared")
    }


    public fun validate(token : String){
        Log.i("ViewModel","this is phone number from confirmViewModel $phoneNumberUser and token $token")
        // TODO realmente igualalarlo al resultado que de la busqueda en la base de datos
        _validate.value = true
    }

    public fun hi(text: TextView){
        Log.i("dominicox", text.text as String)
    }

}