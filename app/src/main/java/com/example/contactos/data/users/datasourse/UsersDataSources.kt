package com.example.contactos.data.users.datasourse

import com.example.contactos.data.users.datasourse.apiservice.model.UsersResponse
import com.example.contactos.utils.ResultDataApi

sealed interface UsersDataSources{
    suspend fun getUsers() : ResultDataApi<List<UsersResponse>>
}