package com.example.contactos.feature.users.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactos.data.users.entities.UserEntity

class ItemUserViewModel: ViewModel() {
    var user: MutableLiveData<UserEntity> = MutableLiveData()
}
