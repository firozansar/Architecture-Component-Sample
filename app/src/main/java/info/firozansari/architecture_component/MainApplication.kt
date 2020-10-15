package info.firozansari.architecture_component

import android.app.Application
import info.firozansari.architecture_component.di.networkModule
import info.firozansari.architecture_component.di.repositoryModule
import info.firozansari.architecture_component.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Firoz Ansari on 15/10/2020.
 */

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger() // Koin Logger
            androidContext(this@MainApplication)
            modules(listOf(viewModelModule, networkModule, repositoryModule))
        }
    }
}