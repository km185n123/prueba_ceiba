package com.example.contactos.data.posts.repository

import com.example.contactos.data.posts.dao.PostDao
import com.example.contactos.data.posts.datasourse.apiservice.model.PostsResponse
import com.example.contactos.data.posts.entities.PostEntity
import com.example.contactos.utils.ResultDataApi

interface PostsRepository {
    suspend fun getPosts(userid: String): ResultDataApi<List<PostEntity>>
     fun loadDataPostsInLocal(posts: List<PostsResponse>, postDao: PostDao)
}
