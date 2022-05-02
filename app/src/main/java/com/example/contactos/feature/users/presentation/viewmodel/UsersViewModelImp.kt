package com.example.contactos.feature.users.presentation.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactos.data.users.entities.UserEntity
import com.example.contactos.feature.users.domain.usecase.GetUsersUseCase
import com.example.contactos.utils.ResultDataApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import io.reactivex.functions.Consumer
@HiltViewModel
class UsersViewModelImp @Inject constructor(
    val getUsersUseCase: GetUsersUseCase
):ViewModel(), UsersViewModel{

    private val progressDialog: MutableLiveData<Boolean> = MutableLiveData()
    val progressDialogLiveData: LiveData<Boolean> = progressDialog

    private val _error: MutableLiveData<String> = MutableLiveData()
    val showErrorLiveData: LiveData<String> = _error

    private val _listIsEmpty: MutableLiveData<Int> = MutableLiveData()
    val listIsEmptyLiveData: LiveData<Int> = _listIsEmpty

    private val _users: MutableLiveData<List<UserEntity>> = MutableLiveData()
    val usersLiveData: LiveData<List<UserEntity>> = _users

    fun fetchUsers(textKey: String?) {
        progressDialog.postValue(true)
         viewModelScope.launch {
            withContext(Dispatchers.IO){
                getUsersUseCase.invoke(textKey).subscribe(Consumer {
                    progressDialog.postValue(false)
                    handleResponse(it)
                })
            }
        }
    }

    override fun handleResponse(it: ResultDataApi<List<UserEntity>>?) {
        when (it) {
            is ResultDataApi.Success -> setData(it.value)
            is ResultDataApi.Failure -> _error.postValue(it.throwable.message)
        }
    }

    override fun setData(value: List<UserEntity>) {
        if (value.isEmpty()){
            _listIsEmpty.postValue(View.VISIBLE)
            _users.postValue(value)
        }else{
           _listIsEmpty.postValue(View.INVISIBLE)
            _users.postValue(value)
        }

    }
}