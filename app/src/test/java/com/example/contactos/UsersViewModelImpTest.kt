package com.example.contactos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.contactos.data.users.entities.UserEntity
import com.example.contactos.feature.users.domain.usecase.GetUsersUseCase
import com.example.contactos.feature.users.presentation.viewmodel.UsersViewModelImp
import com.example.contactos.utils.ResultDataApi
import com.example.contactos.utils.TestCoroutineRule
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UsersViewModelImpTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var viewModel: UsersViewModelImp

    @Mock
    private lateinit var getUsersUseCase: GetUsersUseCase

    lateinit var users : ArrayList<UserEntity>
    @Before
    fun setUp(){
        val getUsersUseCase = Mockito.mock(GetUsersUseCase::class.java)
        viewModel = UsersViewModelImp(getUsersUseCase)

        users  = arrayListOf()
        users.add(
            UserEntity(
                id = "1",
                name = "lucas",
                username = "",
                email = "",
                street = "",
                suite = "",
                city = "",
                zipcode = "",
                lat = "",
                lng = "",
                phone = "",
                website = "",
                nameCompany = "",
                catchPhrase = "",
                bs = ""
            ))

    }

    @Test
    fun addition_isCorrect2(){
        testCoroutineRule.runBlockingTest {
            // mock
            val resultDataApi = ResultDataApi.Success(users.toList())
            val result:Single<ResultDataApi<List<UserEntity>>> = Single.just(resultDataApi)
            val textKey = ""

            Mockito.`when`(getUsersUseCase.invoke(textKey)).thenReturn(result)

            // call
            viewModel.fetchUsers(textKey)
            // test

            val dataResult = viewModel.usersLiveData.value
            assert((dataResult != null) && dataResult.first().name.contains(textKey) )


        }
    }

    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }
}