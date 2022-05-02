package com.example.contactos.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactos.data.posts.dao.PostDao
import com.example.contactos.data.posts.entities.PostEntity
import com.example.contactos.data.users.dao.UserDao
import com.example.contactos.data.users.entities.UserEntity

@Database(entities = [
    UserEntity::class,
    PostEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
}