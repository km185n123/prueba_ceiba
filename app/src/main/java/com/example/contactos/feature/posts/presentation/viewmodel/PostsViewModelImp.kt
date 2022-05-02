package com.example.contactos.feature.posts.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactos.data.posts.entities.PostEntity
import com.example.contactos.feature.posts.domain.usecase.GetPostsUseCase
import com.example.contactos.utils.ResultDataApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import io.reactivex.functions.Consumer
@HiltViewModel
class PostsViewModelImp @Inject constructor(
    val getPostsUseCase: GetPostsUseCase
):ViewModel(), PostsViewModel{

    private val progressDialog: MutableLiveData<Boolean> = MutableLiveData()
    val progressDialogLiveData: LiveData<Boolean> = progressDialog

    private val _error: MutableLiveData<String> = MutableLiveData()
    val showErrorLiveData: LiveData<String> = _error

    private val _posts: MutableLiveData<List<PostEntity>> = MutableLiveData()
    val postsLiveData: LiveData<List<PostEntity>> = _posts


   override fun fetchPosts(userid: String) {
        progressDialog.postValue(true)
         viewModelScope.launch {
            withContext(Dispatchers.IO){
                getPostsUseCase.invoke(userid).subscribe(Consumer {
                    progressDialog.postValue(false)
                    handleResponse(it)
                })
            }
        }
    }

    override fun handleResponse(result: ResultDataApi<List<PostEntity>>?) {
        when (result) {
            is ResultDataApi.Success -> _posts.postValue(result.value)
            is ResultDataApi.Failure -> _error.postValue(result.throwable.message)
        }
    }
}