package org.masud.bookapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.masud.bookapp.core.di.initKoin

class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApplication)
        }
    }
}