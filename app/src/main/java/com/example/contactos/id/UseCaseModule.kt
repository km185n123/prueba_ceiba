package com.example.contactos.id

import com.example.contactos.data.posts.repository.PostsRepositoryImp
import com.example.contactos.data.users.repository.UsersRepositoryImp
import com.example.contactos.feature.posts.domain.usecase.GetPostsUseCaseImp
import com.example.contactos.feature.users.domain.usecase.GetUsersUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun getUsersUseCaseProvider(usersRepository: UsersRepositoryImp)
            = GetUsersUseCaseImp(usersRepository)

    @Provides
    fun getPostsUseCaseProvider(postsRepository: PostsRepositoryImp)
            = GetPostsUseCaseImp(postsRepository)

}