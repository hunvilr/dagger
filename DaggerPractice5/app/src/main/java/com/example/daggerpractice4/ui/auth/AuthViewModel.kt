package com.example.daggerpractice4.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.daggerpractice4.models.User
import com.example.daggerpractice4.network.auth.AuthApi
import com.example.daggerpractice4.ui.auth.AuthResource.Companion.loading
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(val authApi: AuthApi) : ViewModel() {

    private val authUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()
    init {
        Log.d(TAG, "AuthViewModel viewModel is working")
        if(authApi == null) {
            Log.d(TAG, "AuthViewModel auth api is NULL")
        } else {
            Log.d(TAG, "AuthViewModel auth api is NOT NULL")
        }

//        authApi.getUser(1)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(Consumer {
//                Log.d(TAG, "it was success ${it.email}")
//            }, Consumer{
//                "error"
//            })
    }

//    fun authenticateWithId(userId: Int) {
//        val user = authApi.getUser(userId)
//            .subscribeOn(Schedulers.io())
//        val source: LiveData<AuthResource<User>> = LiveDataReactiveStreams.fromPublisher(user.toFlowable())
//        authUser.addSource(source, Observer {
//            authUser.value = it
//            authUser.removeSource(source)
//        })
//    }

    fun authenticateWithId(userId: Int) {
        authUser.value = loading(null)

        val user = authApi.getUser(userId)
            .onErrorReturn {
                val errorUser = User()
                errorUser
            }
            .map {
                return@map if (it.id == -1) {
                    AuthResource.error(null, "could not authenticate")
                } else {
                    AuthResource.authenticated(it)
                }
            }
            .subscribeOn(Schedulers.io())
        val source: LiveData<AuthResource<User>> = LiveDataReactiveStreams.fromPublisher(user.toFlowable())
        authUser.addSource(source, Observer {
            authUser.value = it
            authUser.removeSource(source)
        })
    }

    fun observeUser(): LiveData<AuthResource<User>> {
        return authUser
    }

    companion object {
        const val TAG = "AuthViewModel"
    }
}