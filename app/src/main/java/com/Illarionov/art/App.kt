package com.Illarionov.art

import android.app.Application
import com.Illarionov.art.di.ApplicationComponent
import com.Illarionov.art.di.DaggerApplicationComponent

class App : Application() {

    lateinit var appComponent: ApplicationComponent

    companion object {
        private lateinit var instance: App

        @JvmStatic
        fun getApp() = instance
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder().context(this).build()
        instance = this
    }
}