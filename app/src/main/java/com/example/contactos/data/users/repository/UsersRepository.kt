package com.example.contactos.data.users.repository

import com.example.contactos.data.users.dao.UserDao
import com.example.contactos.data.users.datasourse.apiservice.model.UsersResponse
import com.example.contactos.data.users.entities.UserEntity
import com.example.contactos.utils.ResultDataApi

interface UsersRepository {
     suspend fun getUsers(textKey: String?): ResultDataApi<List<UserEntity>>
    fun loadDataUserInLocal(users: List<UsersResponse>, userDao: UserDao)
}