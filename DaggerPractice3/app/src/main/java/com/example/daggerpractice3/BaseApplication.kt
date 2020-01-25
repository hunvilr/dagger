package com.example.daggerpractice3


import com.example.daggerpractice3.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {
    // For android to return AppComponent as AndroidInjector<BaseApplication> you have to
    // add the AndroidSupportInjectionModule to the AppComponent
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}