package com.example.daggerpractice3

import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Named
import kotlin.properties.Delegates

/**
 *
 * Errors:
 * 1)
 * java.lang.RuntimeException: Unable to start activity
 * ComponentInfo{com.example.daggerpractice2/com.example.daggerpractice2.AuthActivity}:
 * java.lang.RuntimeException: android.app.Application does not implement dagger.android.HasActivityInjector
 * Caused by: java.lang.RuntimeException: android.app.Application does not implement dagger.android.HasActivityInjector
 *
 * declare the android:name=".BaseApplication" in AndroidManifest
 *
 * 2)
 *  kotlin.jvm.KotlinReflectionNotSupportedError: Kotlin reflection implementation is not found at runtime. Make sure you have kotlin-reflect.jar in the classpath
 */
class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var stringValue: String

//  Error: lateinit modifier is not allowed on primitive type properties in Kotlin
//    @Inject
//    lateinit var isAppNotNull:Boolean

//  Error: @inject annotation is not applicable to target member property with delegate
//    @Inject
//    var isAppNotNull: Boolean by Delegates.notNull<Boolean>()

//  Error: Property must be initialized or be abstract
//    @Inject
//    var isAppNotNull:Boolean

    // Error: First, you don't need lateinit, you can leave it as a var, and initialize with an arbitrary value.
    // Second, you must expose a field in order to allow Dagger to inject there. So, here's the solution:
//    @JvmField // expose a field
//    @field:[Inject Named("isAppNotNull")] // leave your annotatios unchanged

    //https://medium.com/@WindRider/correct-usage-of-dagger-2-named-annotation-in-kotlin-8ab17ced6928
//    TL;DR: In Kotlin we inject named fields like this:
//    @field:[Inject Named("api1")] internal lateinit var api: Api
//    // or if you inject a primitive
//    @set:[Inject Named("logoIcon")] var logoIcon: Int = 0

//DO NOT DO THIS IN KOTLIN -> @Inject @Named("api1") internal lateinit var api: Api

// IN JAVA
//class MyPresenter {
//    @Inject @Named("api1")
//    Api api;
//}
    @set:[Inject Named("isAppNotNull")]
    var isAppNotNull: Boolean = false // set a default value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        Log.d(AuthActivity::class.simpleName, "onCreate string value = $stringValue")

        Log.d(AuthActivity::class.simpleName, "onCreate is App Not Null value = $isAppNotNull")
    }
}
