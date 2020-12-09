package com.Illarionov.art

import android.app.Application
import androidx.room.Room
import com.Illarionov.art.factories.ViewModelFactory
import com.Illarionov.art.storage.WorksDataBase

class App : Application(){

    lateinit var factory: ViewModelFactory

    override fun onCreate() {
        super.onCreate()
        val room = Room.databaseBuilder(this, WorksDataBase::class.java, "database").build()
        factory = ViewModelFactory(room)
    }
}