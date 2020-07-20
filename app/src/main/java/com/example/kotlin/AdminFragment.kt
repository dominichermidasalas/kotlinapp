package com.example.kotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.admin.AdminViewModel
import com.example.kotlin.admin.AdminViewModelFactory
import com.example.kotlin.database.User
import com.example.kotlin.database.UserDatabase
import com.example.kotlin.databinding.FragmentAdminBinding
import com.example.kotlin.databinding.FragmentConfirmBinding
import com.example.kotlin.register.RegisterViewModel
import com.example.kotlin.register.RegisterViewModelFactory
import kotlinx.android.synthetic.main.fragment_admin.*
import kotlinx.android.synthetic.main.fragment_admin.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [AdminFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAdminBinding>(inflater, R.layout.fragment_admin, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = UserDatabase.getInstance(application).userDao
        val viewModelFactory = AdminViewModelFactory(dataSource, application)
        val adminViewModel = ViewModelProviders.of(this, viewModelFactory).get(AdminViewModel::class.java)


        binding.lifecycleOwner = this
        var listOfUsers:  List<User> = emptyList()
        //listOfUsers.add(User(1, "1234565777", "234355", true))
        //listOfUsers.add(User(2, "1234567553", "236855", false))

        adminViewModel.livedata.observe(this, Observer { userList ->

                if(userList != null){
                    listOfUsers = userList
                    Log.i("karol", listOfUsers.toString())
                    binding.recyclerView.adapter = RowAdapter(listOfUsers)
                    binding.recyclerView.layoutManager = LinearLayoutManager(context)
                }

            })


        return binding.root
    }
}