package com.irisaco.retrofit_kotlin

import retrofit2.Response
import retrofit2.http.GET

interface PostService {
    @GET("/posts")
    suspend fun getPosts(): Response<Posts>
}