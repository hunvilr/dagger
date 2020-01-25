package com.example.daggerpractice.di

import android.app.Application
import com.example.daggerpractice.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

// AndroidInjector<BaseApplication> means we are going to inject BaseApplication
//into this AppComponent, BaseApplication is a client for the AppComponent service
@Component(
    modules = [AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}