package com.Illarionov.art.di

import android.content.Context
import com.Illarionov.art.view.ui.works.WorksFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(RepositoryModule::class)]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(fragment: WorksFragment)
}