package com.example.kotlin

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kotlin.databinding.FragmentRegisterBinding
import com.example.kotlin.network.SmsApi
import kotlinx.android.synthetic.main.fragment_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(
            inflater, R.layout.fragment_register, container, false)


        binding.continuePhoneButton.setOnClickListener { _ -> sendSMS() }
        //Navigation.createNavigateOnClickListener(RegisterFragmentDirections.actionRegisterFragmentToConfirmFragment("asdfasdfafa"))

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

    private fun sendSMS(){
        SmsApi.retrofitService.getProperties().enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(context, "Connection problem. Try again later", Toast.LENGTH_LONG).show()

            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Toast.makeText(context, response.body(), Toast.LENGTH_LONG).show()
            }

        })
    }
}