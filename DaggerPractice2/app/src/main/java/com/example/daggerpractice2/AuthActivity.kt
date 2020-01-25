package com.example.daggerpractice2

import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        Log.d(AuthActivity::class.simpleName, "onCreate string value = $stringValue")
    }
}
