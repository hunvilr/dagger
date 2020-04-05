package com.example.daggerpractice4

import androidx.lifecycle.MediatorLiveData
import com.example.daggerpractice4.models.User
import com.example.daggerpractice4.ui.auth.AuthResource
import javax.inject.Inject

class SessionManager @Inject constructor(){
    private val cachedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()
}