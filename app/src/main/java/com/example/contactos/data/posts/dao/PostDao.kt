package com.example.contactos.data.posts.dao

import androidx.room.*
import com.example.contactos.data.posts.entities.PostEntity

@Dao
interface PostDao {
    @Query("SELECT * FROM PostEntity  WHERE userId = :userId")
    fun getPosts(userId:String): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(postEntity: PostEntity)

    @Delete
    fun delete(postEntity: PostEntity)

    @Query("DELETE FROM PostEntity")
    fun deleteTable(): Int
}