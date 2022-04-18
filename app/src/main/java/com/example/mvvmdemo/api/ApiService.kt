package com.example.mvvmdemo.api

import com.example.mvvmdemo.models.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api")
    fun getUsersList(@Query("page") pageNumber: Int, @Query("results") results: Int): Call<Users>
}