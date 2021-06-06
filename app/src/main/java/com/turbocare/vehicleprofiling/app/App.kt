package com.turbocare.vehicleprofiling.app

import android.app.Application
import android.content.Context
import com.turbocare.vehicleprofiling.di.DISingleton

class App: Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        DISingleton.initialize(applicationContext())
    }


    companion object {
        private lateinit var instance: App

        fun applicationContext() : Context {
            return instance.applicationContext
        }

        fun getInstance(): App = instance

        fun getDI() = DISingleton.getInstance()
    }
}