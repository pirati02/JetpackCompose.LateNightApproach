package ge.baqar.myapplication.data.storage

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
var storageModule = module {
    single {
        StoragePrefs()
    }
}