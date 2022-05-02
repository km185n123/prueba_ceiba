package com.example.contactos.feature.posts.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactos.data.posts.entities.PostEntity

class ItemPostViewModel: ViewModel() {
    var post: MutableLiveData<PostEntity> = MutableLiveData()
}
