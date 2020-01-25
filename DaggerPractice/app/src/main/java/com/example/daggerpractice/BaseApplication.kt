package com.example.daggerpractice

import com.example.daggerpractice.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * public final class DaggerAppComponent implements AppComponent {
    private DaggerAppComponent(Application application) {}

    public static AppComponent.Builder builder() {
        return new Builder();
    }


    private static final class Builder implements AppComponent.Builder {
        private Application application;

        @Override
        public Builder application(Application application) {
            this.application = Preconditions.checkNotNull(application);
            return this;
        }

        @Override
        public AppComponent build() {
            Preconditions.checkBuilderRequirement(application, Application.class);
            return new DaggerAppComponent(application);
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
 */

class BaseApplication : DaggerApplication() {
    // Binding an application instance to the app component
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}