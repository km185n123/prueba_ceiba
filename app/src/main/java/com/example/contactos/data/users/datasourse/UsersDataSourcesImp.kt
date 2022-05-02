package com.example.contactos.data.users.datasourse

import com.example.contactos.data.users.datasourse.apiservice.UsersApiServices
import com.example.contactos.data.users.datasourse.apiservice.model.UsersResponse
import com.example.contactos.utils.ResultDataApi
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.resume

class UsersDataSourcesImp @Inject constructor(
    val usersApiServices: UsersApiServices
    ) : UsersDataSources {

    override suspend fun getUsers(): ResultDataApi<List<UsersResponse>> =
        suspendCoroutine {
            val result =  usersApiServices.getUsers()
            result.enqueue(object : Callback<List<UsersResponse>>{
                override fun onResponse(call: Call<List<UsersResponse>>, response: Response<List<UsersResponse>>) {
                    when {
                        response.isSuccessful -> {
                            it.resume(ResultDataApi.Success(response.body()!!))
                        }
                        else -> it.resume(ResultDataApi.Failure(Throwable(response.errorBody().toString())))
                    }
                }
                override fun onFailure(call: Call<List<UsersResponse>>, t: Throwable) {
                    it.resume(ResultDataApi.Failure(t))
                }
            })

        }

}