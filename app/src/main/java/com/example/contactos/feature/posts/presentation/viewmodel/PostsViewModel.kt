package com.example.contactos.feature.posts.presentation.viewmodel

import com.example.contactos.data.posts.entities.PostEntity
import com.example.contactos.utils.ResultDataApi

interface PostsViewModel {
    fun fetchPosts(userid: String)
    fun handleResponse(result: ResultDataApi<List<PostEntity>>?)
}
