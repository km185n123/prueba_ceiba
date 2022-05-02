package com.example.contactos.feature.posts.domain.usecase

import com.example.contactos.data.posts.entities.PostEntity
import com.example.contactos.data.posts.repository.PostsRepositoryImp
import com.example.contactos.utils.ResultDataApi
import javax.inject.Inject
import io.reactivex.Single

class GetPostsUseCaseImp @Inject constructor(
    val postsRepository: PostsRepositoryImp
): GetPostsUseCase {
    override suspend fun invoke(userid: String): Single<ResultDataApi<List<PostEntity>>> {
        return  when (val result = postsRepository.getPosts(userid)){
            is ResultDataApi.Success<*> -> Single.just(result)
            is ResultDataApi.Failure<*> -> Single.just(result)
        }
    }


}