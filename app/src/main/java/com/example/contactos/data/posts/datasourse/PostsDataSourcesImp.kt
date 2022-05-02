package com.example.contactos.data.posts.datasourse

import com.example.contactos.data.posts.datasourse.apiservice.PostsApiServices
import com.example.contactos.data.posts.datasourse.apiservice.model.PostsResponse
import com.example.contactos.utils.ResultDataApi
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.resume

class PostsDataSourcesImp @Inject constructor(
    val postsApiServices: PostsApiServices
    ) : PostsDataSources {

    override suspend fun getPosts(userid:String): ResultDataApi<List<PostsResponse>> =
        suspendCoroutine {
            val result =  postsApiServices.getPost(userId = userid)
            result.enqueue(object : Callback<List<PostsResponse>>{
                override fun onResponse(call: Call<List<PostsResponse>>, response: Response<List<PostsResponse>>) {
                    when {
                        response.isSuccessful -> {
                            it.resume(ResultDataApi.Success(response.body()!!))
                        }
                        else -> it.resume(ResultDataApi.Failure(Throwable(response.errorBody().toString())))
                    }
                }
                override fun onFailure(call: Call<List<PostsResponse>>, t: Throwable) {
                    it.resume(ResultDataApi.Failure(t))
                }
            })

        }

}