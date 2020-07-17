package com.example.kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.database.User
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
        var userList = ArrayList<User>()
        userList.add(User(1, "768768687", "767673", true))

        // Inflate the layout for this fragment
        var adminMenu = inflater.inflate(R.layout.fragment_admin, container, false)
        adminMenu.recycler_view.adapter = RowAdapter(userList)
        adminMenu.recycler_view.layoutManager = LinearLayoutManager(this.context)
        return adminMenu
    }
}