package com.Illarionov.art.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(modules = [])
interface ApplicationComponent {
    fun context(): Context

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}