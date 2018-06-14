package com.naveen.interviewapp

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * It will return and ApiService instance that can call login method or any other that you will define in ApiService interface file
 */
class APIExecutor {
    private val apiService: ApiService? = null
/*
    companion object {
        private val BASE_URL = "https://learncodeonline.in/api/android/"
        fun getApiService(): ApiService {
            val okHttpClient = OkHttpClient()
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.newBuilder().connectTimeout((2 * 60 * 1000).toLong(), TimeUnit.SECONDS).readTimeout((2 * 60 * 1000).toLong(), TimeUnit.SECONDS).writeTimeout((2 * 60 * 1000).toLong(), TimeUnit.SECONDS).build()).build()

            return retrofit.create(ApiService::class.java)
        }
    }*/
    companion object {
    private val BASE_URL = "https://learncodeonline.in/api/android/"
    fun getRetrofitInstance(): Retrofit {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build()
            return retrofit;
        }

    }
}
