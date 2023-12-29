package com.example.cafesapp

import android.app.Application
import com.example.cafesapp.data.AppContainer
import com.example.cafesapp.data.DefaultAppContainer

/**
 * The main application class.
 *
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
