package com.example.kotlin

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.ColorSpace
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.kotlin.confirm.ConfirmViewModel
import com.example.kotlin.confirm.ConfirmViewModelFactory
import com.example.kotlin.databinding.ActivityMainBinding
import com.example.kotlin.databinding.FragmentConfirmBinding
import com.example.kotlin.databinding.FragmentRegisterBinding

const val KEY_NUMBER = "phoneNumber"
class ConfirmFragment : Fragment() {

    private lateinit var viewModel: ConfirmViewModel
    private lateinit var viewModelFactory: ConfirmViewModelFactory

    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentConfirmBinding>(inflater, R.layout.fragment_confirm, container, false)

        Log.i("onCreateView", "Called from viewmodelproviders")

        viewModelFactory = ConfirmViewModelFactory(ConfirmFragmentArgs.fromBundle(requireArguments()).phoneNumber.toString())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ConfirmViewModel::class.java)

        binding.confirmViewModel = viewModel

        // TODO recuperar el token que escribio el usuario
        var user_token = "asfdasfas"



        binding.confirmCodeButton.setOnClickListener {
            viewModel.validate(binding.codeEditText.text.toString())
        }

        viewModel.validate.observe(this, Observer {isValid ->
            Log.i("dominic", "este es el color $isValid")
            if (!isValid.toString().toBoolean()) {
                binding.validResult.text = "Invalid Token"
                binding.validResult.visibility = View.VISIBLE
                binding.validResult.setTextColor(Color.RED)
                //Not Valid Token
            }else{
                binding.validResult.text = "Valid Token"
                binding.validResult.visibility = View.VISIBLE
                binding.validResult.setTextColor(Color.GREEN)

                binding.codeEditText.focusable = View.NOT_FOCUSABLE
                //Valid Token
            }
        })


        // TODO poner el verdadero numero de celular
        if (savedInstanceState != null){
            var phone_number = savedInstanceState.getString(KEY_NUMBER)
            Toast.makeText(context, "this is what i restored ${phone_number}", Toast.LENGTH_LONG).show()

        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // TODO poner el verdadero numero de celular
        outState.putString(KEY_NUMBER,"phonenumber123123")
        Log.i("dominic", "on save instance state")
    }


}