package com.example.webservice.network

import com.example.webservice.dto.PostResponse
import com.example.webservice.dto.UserResponse
import com.example.webservice.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {
    // base url: https://gorest.co.in

    // https://gorest.co.in/public/v1/users
    @GET("public/v1/users")
    suspend fun getListOfUsers(): Response<UserResponse>

    @GET("/public/v1/users/{param}/posts")
    suspend fun getListPosts(@Path("param") userId: Int): Response<PostResponse>
}