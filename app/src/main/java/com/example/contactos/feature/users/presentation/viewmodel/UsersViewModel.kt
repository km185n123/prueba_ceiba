package com.example.contactos.feature.users.presentation.viewmodel

import com.example.contactos.data.users.entities.UserEntity
import com.example.contactos.utils.ResultDataApi

interface UsersViewModel {
    fun handleResponse(it: ResultDataApi<List<UserEntity>>?)
    fun setData(value: List<UserEntity>)

}
