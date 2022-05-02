package com.example.contactos.id

import com.example.contactos.feature.posts.domain.usecase.GetPostsUseCaseImp
import com.example.contactos.feature.posts.presentation.viewmodel.PostsViewModelImp
import com.example.contactos.feature.users.domain.usecase.GetUsersUseCaseImp
import com.example.contactos.feature.users.presentation.viewmodel.UsersViewModelImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ViewModelModule {

    @Provides
    fun usersViewModelImProvide(
        getUsersUseCase: GetUsersUseCaseImp
    ) = UsersViewModelImp(getUsersUseCase)

    @Provides
    fun postsViewModelImProvide(
        getPostsUseCase: GetPostsUseCaseImp
    ) = PostsViewModelImp(getPostsUseCase)
}