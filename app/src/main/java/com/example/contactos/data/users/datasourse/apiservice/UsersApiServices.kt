package com.example.contactos.data.users.datasourse.apiservice

import com.example.contactos.data.users.datasourse.apiservice.model.UsersResponse
import retrofit2.Call
import retrofit2.http.GET

interface UsersApiServices {
    @GET(ServicesEndPoints.USERS)
    fun getUsers(
    ): Call<List<UsersResponse>>
}