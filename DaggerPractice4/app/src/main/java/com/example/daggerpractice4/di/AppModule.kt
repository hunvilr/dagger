package com.example.daggerpractice4.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.daggerpractice4.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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
        return application == null
    }

    //Error com.example.daggerpractice4.di.AppComponent (unscoped) may not reference scoped bindings:
    /**
     *   @NonNull
     *   public static RequestManager with(@NonNull View view) {
     *      return getRetriever(view.getContext()).get(view)
     *   }
     */
    @Provides
    @Singleton
    fun providesRequestManager(application: Application, requestOptions: RequestOptions): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    /**
     *   /** Returns a {@link RequestOptions} object with {@link #placeholder(Drawable)} set. */
     *   @NonNull
     *   @CheckResult
     *   public static RequestOptions placeholderOf(@Nullable Drawable placeholder) {
     *      return new RequestOptions().placeholder(placeholder);
     *   }
     */
    @Provides
    @Singleton
    fun providesRequestOptions(): RequestOptions {
        return RequestOptions.placeholderOf(R.drawable.white_background).error(R.drawable.white_background)
    }

    @Provides
    @Singleton
    fun providesAppDrawable(application: Application): Drawable? {
        return ContextCompat.getDrawable(application, R.drawable.logo)
    }
}