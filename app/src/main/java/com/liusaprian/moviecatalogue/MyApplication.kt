package com.liusaprian.moviecatalogue

import android.app.Application
import com.liusaprian.moviecatalogue.di.AppComponent
import com.liusaprian.moviecatalogue.di.DaggerAppComponent

open class MyApplication : Application() {
    val appComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}