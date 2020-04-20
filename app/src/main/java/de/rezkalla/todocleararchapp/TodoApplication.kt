package de.rezkalla.todocleararchapp

import android.app.Application
import de.rezkalla.todocleararchapp.framework.di.ApplicationModule
import de.rezkalla.todocleararchapp.framework.di.DaggerViewModelComponent
import de.rezkalla.todocleararchapp.framework.di.ViewModelComponent


class TodoApplication : Application() {

    companion object {
        private var component: ViewModelComponent? = null

        fun getViewModelComponent(): ViewModelComponent? {
            return component
        }
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(applicationContext))
            .build()
    }


}