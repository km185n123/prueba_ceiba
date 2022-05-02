package com.example.contactos.data.users.repository

import com.example.contactos.data.users.dao.UserDao
import com.example.contactos.data.users.datasourse.UsersDataSourcesImp
import com.example.contactos.data.users.datasourse.apiservice.model.UsersResponse
import com.example.contactos.data.users.entities.UserEntity
import com.example.contactos.utils.ResultDataApi
import javax.inject.Inject

class UsersRepositoryImp @Inject constructor(
    val userDao: UserDao,
    val usersDataSources: UsersDataSourcesImp,
) : UsersRepository {

    override suspend fun getUsers(textKey: String?): ResultDataApi<List<UserEntity>> {

        return userDao.getUsers().let {
            if (it.isEmpty()) {
                when (val result = usersDataSources.getUsers()) {
                    is ResultDataApi.Success<*> -> {
                        val users = result.value as List<UsersResponse>
                        loadDataUserInLocal(users, userDao)
                        ResultDataApi.Success(userDao.getUsers())
                    }
                    is ResultDataApi.Failure<*> -> ResultDataApi.Failure(result.throwable)
                }
            } else
                ResultDataApi.Success(it)
        }
    }

    override fun loadDataUserInLocal(users: List<UsersResponse>, userDao: UserDao) {
        users.map { user ->
            val userEntity = UserEntity(
                id = user.id.toString(),
                name = user.name,
                username = user.username,
                email = user.email,
                street = user.address.street,
                suite = user.address.suite,
                city = user.address.city,
                zipcode = user.address.zipcode,
                lat = user.address.geo.lat,
                lng = user.address.geo.lng,
                phone = user.phone,
                website = user.website,
                nameCompany = user.company.name,
                catchPhrase = user.company.catchPhrase,
                bs = user.company.bs
            )
            userDao.insert(userEntity)
        }
    }
}


