package com.example.daggerpractice4.di.auth

import androidx.lifecycle.ViewModel
import com.example.daggerpractice4.di.ViewModelKey
import com.example.daggerpractice4.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindPokemonResourceViewModel(viewModel: AuthViewModel): ViewModel
}