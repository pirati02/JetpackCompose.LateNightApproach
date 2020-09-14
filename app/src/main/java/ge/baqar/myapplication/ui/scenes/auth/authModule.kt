package ge.baqar.myapplication.ui.scenes.auth

import ge.baqar.myapplication.data.remote.service.AuthService
import ge.baqar.myapplication.data.repository.AccountRepositoryImpl
import ge.baqar.myapplication.ui.scenes.auth.login.LoginViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
val authModule = module {
    single { provideAuthService(get()) }
    single { AccountRepositoryImpl(get(), get()) }

    //viewModel
    single { LoginViewModel(get()) }
}


fun provideAuthService(retrofit: Retrofit): AuthService = retrofit.create(AuthService::class.java)