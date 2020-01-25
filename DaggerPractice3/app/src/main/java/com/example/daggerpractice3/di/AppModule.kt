package com.example.daggerpractice3.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun someString(): String {
            return "I am some string"
        }
    }

    @Provides       //Error if you dont annotate: java.lang.Boolean cannot be provided without an @Provides-annotated method
    fun getApp(application: Application): Boolean {
        return application != null
    }
}