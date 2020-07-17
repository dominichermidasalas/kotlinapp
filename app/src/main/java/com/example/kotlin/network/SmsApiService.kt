package com.example.kotlin.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://dummy.restapiexample.com/api/v1/employees"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface SmsApiService{
    @GET("api/v1/employees")
    fun getProperties():
            Call<String>
}

object SmsApi{
    val retrofitService: SmsApiService by lazy {
        retrofit.create(SmsApiService::class.java)
    }
}

