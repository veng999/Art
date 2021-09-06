package com.Illarionov.art

import android.app.Application
import com.Illarionov.art.di.ApplicationComponent
import com.Illarionov.art.di.DaggerApplicationComponent

class App : Application() {

    val appComponent by lazy(LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent.builder().context(this).build()
    }

    companion object {
        private lateinit var instance: App

        @JvmStatic
        fun getApp() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}