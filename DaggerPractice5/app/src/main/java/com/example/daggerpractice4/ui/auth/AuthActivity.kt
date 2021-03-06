package com.example.daggerpractice4.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import com.example.daggerpractice4.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Named

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
class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {
    @Inject
    lateinit var stringValue: String

//  https://stackoverflow.com/questions/44381791/lateinit-modifier-is-not-allowed-on-primitive-type-properties-in-kotlin
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

//    https://medium.com/@WindRider/correct-usage-of-dagger-2-named-annotation-in-kotlin-8ab17ced6928
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

    @Inject
    lateinit var requestManager: RequestManager

// Error: 'lateinit' modifier is not allowed on properties of nullable types
//    @Inject
//    lateinit var logo: Drawable?

    @set:[Inject Named("logo")]
    var logo: Drawable? = null

    lateinit var imageView: ImageView


    @Inject
    lateinit var authViewModel: AuthViewModel

    lateinit var userId: EditText

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        Log.d(AuthActivity::class.simpleName, "onCreate string value = $stringValue")

        Log.d(AuthActivity::class.simpleName, "onCreate is App Not Null value = $isAppNotNull")
        imageView = findViewById(R.id.login_logo)
        userId = findViewById(R.id.user_id_input)
        progressBar = findViewById(R.id.progress_bar)

        findViewById<Button>(R.id.login_button).setOnClickListener(this)
        setLogo()

        subscribeObservers()
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(imageView)
    }

    override fun onClick(v: View?) {
       when(v?.id) {
           R.id.login_button -> attemptLogin()
       }
    }

    private fun attemptLogin() {
        if(TextUtils.isEmpty(userId.text.toString())) return
        authViewModel.authenticateWithId(Integer.parseInt(userId.text.toString()))
    }

    private fun subscribeObservers() {
//        authViewModel.observeUser().observe(this, Observer {
//            if(it != null) {
//                Log.d(AuthActivity::class.simpleName, "onChanged " + it.email)
//            }
//        })

        authViewModel.observeUser().observe(this, Observer {
            when(it.authstatus) {
                AuthResource.Authstatus.LOADING -> {
                    showProgressBar(true)
                }
                AuthResource.Authstatus.AUTHENTICATED -> {
                    showProgressBar(false)
                    Log.d(AuthActivity::class.simpleName, "onChanged " + it.data?.email)
                }
                AuthResource.Authstatus.ERROR -> {
                    showProgressBar(false)
                    Log.d(AuthActivity::class.simpleName, "NOT__AUTHENTICATED ")
                    Toast.makeText(this, "${it.message} Did you enter a number between 1 and 10", Toast.LENGTH_SHORT).show()
                }
                AuthResource.Authstatus.NOT__AUTHENTICATED -> {
                    showProgressBar(false)
                }
            }
        })
    }

    private fun showProgressBar(isVisible: Boolean) {
        if(isVisible) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}
