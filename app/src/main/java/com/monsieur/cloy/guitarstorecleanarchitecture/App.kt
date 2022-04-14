package com.monsieur.cloy.guitarstorecleanarchitecture

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.monsieur.cloy.guitarstorecleanarchitecture.di.appModule
import com.monsieur.cloy.guitarstorecleanarchitecture.di.dataModule
import com.monsieur.cloy.guitarstorecleanarchitecture.di.domainModule
import kotlinx.coroutines.DEBUG_PROPERTY_NAME
import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        System.setProperty(DEBUG_PROPERTY_NAME, DEBUG_PROPERTY_VALUE_ON)
        startKoin {
            androidLogger(Level.ERROR)
            modules(listOf(appModule, domainModule, dataModule))
            androidContext(this@App)
        }
        setTheme()
    }

    fun setTheme() {
        val sharedPreferences = getSharedPreferences("theme", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("dark", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            sharedPreferences.edit().putBoolean("light", false).apply()
            sharedPreferences.edit().putBoolean("dark", true).apply()
        } else if (sharedPreferences.getBoolean("light", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPreferences.edit().putBoolean("light", true).apply()
            sharedPreferences.edit().putBoolean("dark", false).apply()
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPreferences.edit().putBoolean("light", true).apply()
            sharedPreferences.edit().putBoolean("dark", false).apply()
        }
    }
}