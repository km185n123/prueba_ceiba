package com.example.contactos.feature.posts.domain.usecase

import com.example.contactos.data.posts.entities.PostEntity
import com.example.contactos.utils.ResultDataApi
import io.reactivex.Single

interface GetPostsUseCase {
     suspend fun invoke(userid: String): Single<ResultDataApi<List<PostEntity>>>

}
