package com.example.boardgamesapp

import android.app.Application
import com.example.boardgamesapp.data.AppContainer
import com.example.boardgamesapp.data.DefaultAppContainer

/**
 * The main application class.
 * @property container the app container.
 */
class CafeApplication : Application() {
    lateinit var container: AppContainer

    /**
     * Initializes the app container as a [DefaultAppContainer].
     */
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}
