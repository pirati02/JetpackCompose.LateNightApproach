package ge.baqar.myapplication

import org.koin.dsl.module

val appModule = module {
    factory { App() }
}