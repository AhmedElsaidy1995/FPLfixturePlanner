package com.example.sasadoe.fplfixtureplanner.Controller

import android.app.Application
import com.example.sasadoe.fplfixtureplanner.Utilities.SharedPrefs

class App : Application() {
    companion object {
        lateinit var prefs : SharedPrefs
    }

    override fun onCreate() {
        prefs = SharedPrefs(applicationContext)
        super.onCreate()
    }
}