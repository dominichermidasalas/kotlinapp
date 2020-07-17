package com.example.kotlin.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {


    @Insert
    fun insert(user: User)


    @Update
    fun update(user: User)


    @Query( "SELECT * FROM users where phone_number = :phoneNumber")
    fun get(phoneNumber: String) : User

    @Query( "SELECT * FROM users ")
    fun getAllUsers(): List<User>
}