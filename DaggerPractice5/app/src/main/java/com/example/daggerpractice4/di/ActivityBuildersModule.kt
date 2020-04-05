package com.example.daggerpractice4.di

import com.example.daggerpractice4.di.auth.AuthModule
import com.example.daggerpractice4.di.auth.AuthViewModelsModule
import com.example.daggerpractice4.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * https://stackoverflow.com/questions/48386794/provides-and-binds-methods-in-same-class-kotlin
 * After dagger 2.11 we can use a @Binds annotation and mark our Module as abstract in this
 * case which is more efficient than a concrete.
 * If my Module has both @Provides and @Binds methods, I have two options :
 * Simplest would be to mark your @Provides instance methods as static.
 * If it is necessary to keep them as instance methods, then you can split your module
 * into two and extract out all the @Binds methods into an abstract Module.
 * The second option works fine in Java and Kotlin but the first option works fine in Java
 * but I don't know how to implement the same in Kotlin.
 * If I move @Provides method to Companion object it throw
 * Error:(30, 1) error: @Provides methods can only be present within a @Module or @ProducerModule.
 * How can do this in Kotlin.
 *
 */
//@Module
//abstract class ActivityBuildersModule {
//
//    //has to be abstract, means we can inject anything into AuthActivity
//    @ContributesAndroidInjector
//    abstract fun contributesAuthActivity() : AuthActivity
//
//    //Error ActivityBuildersModule is abstract and has instance @Provides methods.
//    // Consider making the methods static or including a non-abstract subclass of the module instead.
//    @Provides
//    fun someString(): String {
//        return "I am some string"
//    }
//}

@Module
abstract class ActivityBuildersModule {

    //has to be abstract, means we can inject anything into AuthActivity
    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class, AuthModule::class]
    )
    abstract fun contributesAuthActivity() : AuthActivity
}