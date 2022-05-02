package com.example.contactos.data.posts.datasourse.apiservice

import com.example.contactos.data.posts.datasourse.apiservice.model.PostsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApiServices {
    @GET(ServicesEndPoints.POSTS)
    fun getPost(
        @Query("userId") userId: String,
    ): Call<List<PostsResponse>>
}