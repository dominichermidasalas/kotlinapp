package com.example.kotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =[ValidPhone::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase(){

}