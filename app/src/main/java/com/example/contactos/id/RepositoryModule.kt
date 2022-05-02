package com.example.contactos.id

import com.example.contactos.data.posts.dao.PostDao
import com.example.contactos.data.posts.datasourse.PostsDataSourcesImp
import com.example.contactos.data.posts.repository.PostsRepositoryImp
import com.example.contactos.data.users.dao.UserDao
import com.example.contactos.data.users.datasourse.UsersDataSourcesImp
import com.example.contactos.data.users.repository.UsersRepositoryImp
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun gsonProvide(): Gson = Gson()

    @Singleton
    @Provides
    fun usersRepositoryRepositoryProvide(
         userDao: UserDao,
         usersDataSources: UsersDataSourcesImp,
    ) = UsersRepositoryImp(userDao, usersDataSources )

    @Singleton
    @Provides
    fun postsRepositoryProvide(
        postDao: PostDao,
        postsDataSources: PostsDataSourcesImp,
    ) = PostsRepositoryImp(postDao, postsDataSources )



}