package com.example.contactos.id

import android.content.Context
import androidx.room.Room
import com.example.contactos.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBase {

    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "contact_database").build()

    @Singleton
    @Provides
    fun userDaoProvider(database: AppDatabase) = database.userDao()

    @Singleton
    @Provides
    fun postDaoProvider(database: AppDatabase) = database.postDao()


}