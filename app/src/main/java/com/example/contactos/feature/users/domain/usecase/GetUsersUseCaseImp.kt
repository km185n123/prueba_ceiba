package com.example.contactos.feature.users.domain.usecase

import com.example.contactos.data.users.entities.UserEntity
import com.example.contactos.data.users.repository.UsersRepositoryImp
import com.example.contactos.utils.ResultDataApi
import javax.inject.Inject
import io.reactivex.Single

class GetUsersUseCaseImp @Inject constructor(
    val usersRepository: UsersRepositoryImp
): GetUsersUseCase {
    override suspend fun invoke(textKey: String?): Single<ResultDataApi<List<UserEntity>>> {
        return  when (val result = usersRepository.getUsers(textKey)){
            is ResultDataApi.Success<*> -> {
              filterUser(textKey, result as ResultDataApi.Success<List<UserEntity>>)
            }
            is ResultDataApi.Failure<*> -> Single.just(result)
        }
    }

    override fun filterUser(textKey: String?, result: ResultDataApi.Success<List<UserEntity>>): Single<ResultDataApi<List<UserEntity>>> {
        return if (textKey != null){
            val filter = result.value.filter { user-> user.name.startsWith(textKey,true) }
            Single.just( ResultDataApi.Success(filter))
        }else{
            Single.just(result)
        }
    }
}