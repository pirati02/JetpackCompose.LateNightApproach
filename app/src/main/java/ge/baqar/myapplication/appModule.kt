package ge.baqar.myapplication

import org.koin.dsl.module

val appModule = module {
    single { App() }
}