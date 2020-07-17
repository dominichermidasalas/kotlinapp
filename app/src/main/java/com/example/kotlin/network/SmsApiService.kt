package com.example.kotlin.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface SmsApiService{
    @GET("todos/1")
    fun getProperties():
            Call<String>
}

object SmsApi{
    val retrofitService: SmsApiService by lazy {
        retrofit.create(SmsApiService::class.java)
    }
}

