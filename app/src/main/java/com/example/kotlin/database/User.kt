package com.example.kotlin.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Users")
data class User(

    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0,

    @ColumnInfo(name = "phone_number")
    var phoneNumber : String = "",

    @ColumnInfo(name = "token")
    var token : String = ""
)