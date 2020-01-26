package com.example.daggerpractice4.di

import android.app.Application
import com.example.daggerpractice4.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class
    ]
)
@Singleton
/**
 *
 * error: [Dagger/IncompatiblyScopedBindings] com.example.daggerpractice4.di.AppComponent
 * (unscoped) may not reference scoped bindings:
 * Task :app:buildInfoGeneratorDebug
 * Since AppModule has singleton and the appcomponent is not singleton the above error
 */
interface AppComponent: AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}