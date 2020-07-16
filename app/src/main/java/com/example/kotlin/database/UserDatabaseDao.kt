package com.example.kotlin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDatabaseDao {
    @Insert
    fun insert(userPhone: UserPhone)

    @Update
    fun update(userPhone: UserPhone)

    @Query("SELECT * from phone_validation_table WHERE userId = :key")
    fun get(key: Long): UserPhone?

    @Query("DELETE FROM phone_validation_table")
    fun clear()

    @Query("SELECT * FROM phone_validation_table ORDER BY userId DESC")
    fun getAllNights(): LiveData<List<UserPhone>>

    @Query("SELECT * FROM phone_validation_table ORDER BY userId DESC LIMIT 1")
    fun getLastAttempt(): UserPhone?
}