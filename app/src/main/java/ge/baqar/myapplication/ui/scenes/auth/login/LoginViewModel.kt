package ge.baqar.myapplication.ui.scenes.auth.login

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import ge.baqar.myapplication.data.dto.auth.LoginRequest
import ge.baqar.myapplication.data.repository.AccountRepositoryImpl
import ge.baqar.myapplication.ui.scenes.arch.ReactiveViewModel
import ge.baqar.myapplication.ui.scenes.arch.fold
import ge.baqar.myapplication.ui.scenes.auth.state.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class LoginViewModel(
    private val authRepository: AccountRepositoryImpl
) : ReactiveViewModel<AuthAction, AuthState>(AuthState.DEFAULT) {


    @RequiresApi(Build.VERSION_CODES.M)
    private fun doLogin(userName: String?, password: String?): AuthState = runBlocking {
        withContext(viewModelScope.coroutineContext) {
            authRepository.login(
                LoginRequest().create(userName, password)
            ).fold(
                onLeft = { state.value.copy(error = it.exception?.message) },
                onRight = {
                    state.value.copy(
                        userInfo = UserInfo(
                            it.userId,
                            it.token,
                            it.firstName ?: it.firstNameEn,
                            it.lastName ?: it.lastNameEn,
                            it.mobile,
                            it.personalNumber,
                            it.personId,
                            it.accountActivated,
                            it.birthDate,
                            it.email,
                            it.personIsLimited,
                            it.isSuperUser
                        )
                    )
                }
            )
        }
    }

    override fun handleIntent(intent: AuthAction): AuthState {
        return when (intent) {
            is LoginAction -> doLogin(intent.email, intent.password)
            is RegisterAction -> TODO()
            is SetInitialStateAction -> AuthState.DEFAULT
        }
    }
}