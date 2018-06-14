package com.naveen.interviewapp


import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("datastructure")
    fun getListResources(): Call<ApiResponse>


}

