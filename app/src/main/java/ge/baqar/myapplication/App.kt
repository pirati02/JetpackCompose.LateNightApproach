package ge.baqar.myapplication

import android.app.Application
import ge.baqar.myapplication.data.remote.networkModule
import ge.baqar.myapplication.ui.scenes.auth.authModule
import ge.dev.baqar.mycoroutines.utility.utilityModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(mutableListOf(
                appModule,

                utilityModule,
                networkModule,

                authModule
            ))
        }
    }
}