package ge.baqar.myapplication.ui.scenes.auth

import ge.baqar.myapplication.app.Store
import ge.baqar.myapplication.data.remote.service.AuthService
import ge.baqar.myapplication.data.repository.AccountRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
val authModule = module {
    single { provideAuthService(get()) }
    single { AccountRepositoryImpl(get(), get()) }

    //viewModel
    single { Store(get(), get()) }
    single { AuthViewModel() }
}


fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)