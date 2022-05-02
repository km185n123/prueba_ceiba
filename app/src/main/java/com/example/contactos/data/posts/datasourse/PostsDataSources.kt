package com.example.contactos.data.posts.datasourse

import com.example.contactos.data.posts.datasourse.apiservice.model.PostsResponse
import com.example.contactos.utils.ResultDataApi

interface PostsDataSources {
    suspend fun getPosts(userid:String): ResultDataApi<List<PostsResponse>>
}
