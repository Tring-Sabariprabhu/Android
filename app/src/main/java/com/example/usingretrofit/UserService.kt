package com.example.usingretrofit

import com.example.usingretrofit.API.ApiParams.LoginParams
import com.example.usingretrofit.Data.User
import com.example.usingretrofit.API.Response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface UserService {
    @GET("/users")
    suspend fun getUsers(): Response<List<User>>

    @POST("/login")
    suspend fun login(@Body request: LoginParams): Response<LoginResponse>
}