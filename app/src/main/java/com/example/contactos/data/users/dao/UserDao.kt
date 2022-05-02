package com.example.contactos.data.users.dao

import androidx.room.*
import com.example.contactos.data.users.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity ")
    fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE name LIKE :textKey")
    fun getUsers(textKey: String?): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(UserEntity: UserEntity)

    @Delete
    fun delete(UserEntity: UserEntity)

    @Query("DELETE FROM UserEntity")
    fun deleteTable(): Int
}