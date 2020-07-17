package com.example.kotlin

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kotlin.confirm.ConfirmViewModel
import com.example.kotlin.databinding.FragmentRegisterBinding
import com.example.kotlin.network.SmsApi
import com.example.kotlin.register.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {
    private lateinit var viewModel: RegisterViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(
            inflater, R.layout.fragment_register, container, false)
        navController = findNavController()
        binding.continuePhoneButton.setOnClickListener {
            sendSMS(phoneEditText.text.toString())
        }
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

    private fun sendSMS(phoneNumber: String){
        //SmsApi.retrofitService.getProperties().enqueue(object : Callback<String> {
        var basic = Credentials.basic("AC3c2f400abbb0c6024f357XXXXXXXXXX", "d885a933425de52XXXXXXXXXXXX")
        SmsApi.retrofitService.postNumber(basic, "+523129430816", "+12183068958", "https://demo.twilio.com/owl.png", "Hola").enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(context, "Connection problem. Try again later", Toast.LENGTH_LONG).show()

            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Toast.makeText(context, "response: " + response.body(), Toast.LENGTH_LONG).show()
                Log.i("POST", "response: " + response.body())
                // Toast.makeText(context, "phone: " + phoneNumber, Toast.LENGTH_LONG).show()
                val bundle = bundleOf("phoneNumber" to phoneNumber)
                navController.navigate(R.id.confirmFragment, bundle)
            }

        })
    }
}