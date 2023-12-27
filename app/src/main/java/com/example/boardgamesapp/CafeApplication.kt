package com.example.boardgamesapp

import android.app.Application
import com.example.boardgamesapp.data.AppContainer
import com.example.boardgamesapp.data.DefaultAppContainer

class CafeApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}
