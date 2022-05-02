package com.example.contactos.id

import com.example.contactos.BuildConfig
import com.example.contactos.data.posts.datasourse.PostsDataSourcesImp
import com.example.contactos.data.posts.datasourse.apiservice.PostsApiServices
import com.example.contactos.data.users.datasourse.UsersDataSourcesImp
import com.example.contactos.data.users.datasourse.apiservice.UsersApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun httpLoggingInterceptorProvide(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun clientHttpProvide(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient
            = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun retrofitProvide(okHttpClient: OkHttpClient): Retrofit
            = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun usersApiServicesProvide(retrofit: Retrofit): UsersApiServices
            = retrofit.create(UsersApiServices::class.java)
    @Singleton
    @Provides
    fun postsApiServicesProvide(retrofit: Retrofit): PostsApiServices
            = retrofit.create(PostsApiServices::class.java)

    @Singleton
    @Provides
    fun usersDataSourceProvide(usersApiServices: UsersApiServices)
            = UsersDataSourcesImp(usersApiServices)

    @Singleton
    @Provides
    fun postsDataSourcesProvide(postsApiServices: PostsApiServices)
            = PostsDataSourcesImp(postsApiServices)

}