package com.example.contactos.data.posts.repository

import com.example.contactos.data.posts.dao.PostDao
import com.example.contactos.data.posts.datasourse.PostsDataSourcesImp
import com.example.contactos.data.posts.datasourse.apiservice.model.PostsResponse
import com.example.contactos.data.posts.entities.PostEntity
import com.example.contactos.utils.ResultDataApi
import javax.inject.Inject

class PostsRepositoryImp @Inject constructor(
    val postDao: PostDao,
    val postsDataSources: PostsDataSourcesImp,
) : PostsRepository {

    override suspend fun getPosts(userid: String): ResultDataApi<List<PostEntity>> {

        return postDao.getPosts(userid).let {
            if (it.isEmpty()) {
                when (val result = postsDataSources.getPosts(userid)) {
                    is ResultDataApi.Success<*> -> {
                        val posts = result.value as List<PostsResponse>
                        loadDataPostsInLocal(posts, postDao)
                        ResultDataApi.Success(postDao.getPosts(userid))
                    }
                    is ResultDataApi.Failure<*> -> ResultDataApi.Failure(result.throwable)
                }
            } else
                ResultDataApi.Success(it)
        }
    }

    override fun loadDataPostsInLocal(posts: List<PostsResponse>, postDao: PostDao) {
        posts.map { post ->
            val postEntity = PostEntity(
                userId = post.userId.toString(),
                id = post.id.toString(),
                title = post.title,
                body = post.body
            )
            postDao.insert(postEntity)
        }
    }
}


