package com.example.kotlin.network
import okhttp3.Credentials
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

private const val TEST_URL = "https://postman-echo.com/"
private const val TEST_ENDPOINT = "post"
private const val PROD_URL = "https://api.twilio.com/"
private const val PROD_ENDPOINT = "2010-04-01/Accounts/AC3c2f400abbb0c6024f357deee75a3ce5/Messages.json"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(PROD_URL)
    .build()

interface SmsApiService{
    @GET("get?foo1=bar1&foo2=bar2")
    fun getProperties():
            Call<String>
    @FormUrlEncoded
    @POST(PROD_ENDPOINT)
    fun postNumber(
        @Header("Authorization") auth: String,
        @Field("To") to: String,
        @Field("From") from: String,
        @Field("MediaUrl") mediaUrl: String,
        @Field("Body") body: String
    ):
            Call<String>
}

object SmsApi{
    val retrofitService: SmsApiService by lazy {
        retrofit.create(SmsApiService::class.java)
    }
}