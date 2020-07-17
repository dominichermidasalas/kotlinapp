package com.example.kotlin

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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
            var text = binding.phoneEditText.text.toString()
            Log.i("async","continuebutton $text")
            registerViewModel.register(text)
            Log.i("async","navigation $text")
            view.findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToConfirmFragment(text))
        }


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