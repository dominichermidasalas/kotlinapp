package com.example.kotlin

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.fragment.findNavController
import com.example.kotlin.database.User
import com.example.kotlin.database.UserDatabase
import com.example.kotlin.databinding.FragmentRegisterBinding
import com.example.kotlin.register.RegisterViewModel
import com.example.kotlin.register.RegisterViewModelFactory
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(
            inflater, R.layout.fragment_register, container, false)


        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDao
        val viewModelFactory = RegisterViewModelFactory(dataSource, application)
        val registerViewModel = ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel::class.java)

        binding.lifecycleOwner = this


        binding.continuePhoneButton.setOnClickListener{ view: View ->
            registerViewModel.observar = true
            var text = binding.phoneEditText.text.toString()
            registerViewModel.register(text)
        }


        registerViewModel.allReadyvalidated.observe(this, Observer {allReadyvalidated ->
            Log.i("raro", "VALOR DE ALLREADYVALIDATED $allReadyvalidated")
            if(allReadyvalidated) {
                Toast.makeText(context, "Phone number allready validated", Toast.LENGTH_LONG).show()
            }else{
                if (registerViewModel.observar){
                    var text = binding.phoneEditText.text.toString()
                    registerViewModel.create(text)
                    this.findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToConfirmFragment(text))
                    registerViewModel.observar = false
                }
            }
        })



        setHasOptionsMenu(true)
        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}