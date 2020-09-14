package ge.dev.baqar.mycoroutines.utility

import com.github.brewin.mvicoroutines.utility.NetworkStatus
import org.koin.dsl.module

val utilityModule = module {
    factory { NetworkStatus(get()) }
}