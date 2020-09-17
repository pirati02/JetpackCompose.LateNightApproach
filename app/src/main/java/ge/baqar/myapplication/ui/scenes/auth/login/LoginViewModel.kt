package ge.baqar.myapplication.ui.scenes.auth.login

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import ge.baqar.myapplication.data.dto.auth.LoginRequest
import ge.baqar.myapplication.data.repository.AccountRepositoryImpl
import ge.baqar.myapplication.data.storage.StoragePrefs
import ge.baqar.myapplication.ui.scenes.arch.ReactiveViewModel
import ge.baqar.myapplication.ui.scenes.arch.fold
import ge.baqar.myapplication.ui.scenes.auth.state.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class LoginViewModel(
    private val authRepository: AccountRepositoryImpl,
    private val storage: StoragePrefs
) : ReactiveViewModel<AuthAction, UserState?>(UserState.DEFAULT) {


    override fun handleIntent(action: AuthAction): UserState? {
        return when (action) {
            is LoginAction -> doLogin(action.email, action.password)
            is StoreUserInfoAction -> storeUserInfo(action.userInfo)
            is RegisterAction -> TODO()
            is SetInitialStateAction -> UserState.DEFAULT
            is SetUserInfo -> setUserInfo(action.userInfo)
        }
    }

    private fun setUserInfo(userInfo: UserState.UserInfo?): UserState? {
        return state?.value?.copy(userInfo = userInfo)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun doLogin(userName: String?, password: String?): UserState? = runBlocking {
        withContext(viewModelScope.coroutineContext) {
            authRepository.login(
                LoginRequest().create(userName, password)
            ).fold(
                onLeft = { state?.value?.copy(error = it.exception?.message) },
                onRight = {
                    state?.value?.copy(
                        userInfo = UserState.UserInfo(
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

    private fun storeUserInfo(userInfo: UserState.UserInfo?): UserState? {
        userInfo?.asStorage()?.let { info ->
            storage.setUserInfo(info)
        }
        return state?.value
    }
}