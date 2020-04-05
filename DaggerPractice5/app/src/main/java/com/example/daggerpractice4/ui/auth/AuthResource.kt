package com.example.daggerpractice4.ui.auth

import com.example.daggerpractice4.models.User

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}
class AuthResource<out T>(val authstatus: Authstatus, val data: T? = null,val message: String? = null) {
    enum class Authstatus {
        LOADING,
        AUTHENTICATED,
        NOT__AUTHENTICATED,
        ERROR
    }

    companion object {
        fun <T> authenticated(data: T): AuthResource<T> {
            return AuthResource(
                Authstatus.AUTHENTICATED,
                data,
                null
            )
        }

        fun <T> error(data: T? = null, message: String?): AuthResource<T> {
            return AuthResource(
                Authstatus.ERROR,
                data,
                message
            )
        }

        fun <T> loading(data: T? = null): AuthResource<T> {
            return AuthResource(
                Authstatus.LOADING,
                data,
                null
            )
        }

        fun <T> logout(): AuthResource<T> {
            return AuthResource(
                Authstatus.NOT__AUTHENTICATED,
                null,
                null
            )
        }
    }
}