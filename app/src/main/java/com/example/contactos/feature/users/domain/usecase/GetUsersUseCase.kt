package com.example.contactos.feature.users.domain.usecase

import com.example.contactos.data.users.entities.UserEntity
import com.example.contactos.utils.ResultDataApi
import io.reactivex.Single

interface GetUsersUseCase {
    suspend fun invoke(textKey: String?): Single<ResultDataApi<List<UserEntity>>>
    fun filterUser(textKey: String?, result: ResultDataApi.Success<List<UserEntity>>): Single<ResultDataApi<List<UserEntity>>>
}