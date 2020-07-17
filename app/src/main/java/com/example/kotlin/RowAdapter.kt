package com.example.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.database.User
import kotlinx.android.synthetic.main.row_item.view.*

class RowAdapter(private val userList: List<User>) : RecyclerView.Adapter<RowAdapter.ItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.textViewPhone.text = currentItem.phoneNumber
        holder.textViewValidated.text = currentItem.validated.toString()
    }

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textViewPhone: TextView = itemView.phoneAdminText
        val textViewValidated: TextView = itemView.validatedAdminText
    }
}