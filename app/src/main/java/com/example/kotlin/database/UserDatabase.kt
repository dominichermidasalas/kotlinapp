package com.example.kotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[UserPhone::class], version = 1, exportSchema = false)
abstract class UserDatabase: RoomDatabase(){
    abstract class UserDatabase: RoomDatabase(){
        abstract val UserDatabaseDao: UserDatabaseDao
        companion object{

            @Volatile
            private var INSTANCE: UserDatabase? = null

            fun getInstance(context: Context): UserDatabase{
                synchronized(this){
                    var instance =  INSTANCE
                    if(instance == null){
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            UserDatabase::class.java,
                            "company_database"
                        ).fallbackToDestructiveMigration().build()
                        INSTANCE = instance
                    }
                    return instance
                }

            }
        }
    }

}