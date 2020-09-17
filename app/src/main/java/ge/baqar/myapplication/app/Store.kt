package ge.baqar.myapplication.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import ge.baqar.myapplication.data.dto.auth.LoginRequest
import ge.baqar.myapplication.data.repository.AccountRepositoryImpl
import ge.baqar.myapplication.data.storage.StoragePrefs
import ge.baqar.myapplication.ui.scenes.arch.ReactiveViewModel
import ge.baqar.myapplication.ui.scenes.arch.fold
import ge.baqar.myapplication.ui.scenes.auth.state.AppAction
import ge.baqar.myapplication.ui.scenes.auth.state.AppState
import ge.baqar.myapplication.ui.scenes.dashboard.state.DashboardState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class Store(
    private val storagePrefs: StoragePrefs,
    private val authRepository: AccountRepositoryImpl
) : ReactiveViewModel<AppAction, AppState>(AppState.DEFAULT) {
    override fun handleIntent(action: AppAction): AppState {
        return when (action) {
            AppAction.SetInitialStateAction -> AppState.DEFAULT
            is AppAction.LoginAction -> doLogin(action.email, action.password)
            is AppAction.StoreUserInfoAction -> storeUserInfo(action.userInfo)
            is AppAction.SetUserInfo -> setUserInfo(action.userInfo)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun doLogin(userName: String?, password: String?): AppState = runBlocking {
        withContext(viewModelScope.coroutineContext) {
            authRepository.login(
                LoginRequest().create(userName, password)
            ).fold(
                onLeft = { state.value.copy(error = it.exception?.message) },
                onRight = {
                    state.value.copy(
                        userInfo = AppState.UserInfo(
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

    private fun storeUserInfo(userInfo: AppState.UserInfo?): AppState {
        userInfo?.asStorage()?.let { info ->
            storagePrefs.setUserInfo(info)
        }
        return state.value
    }

    private fun setUserInfo(userInfo: AppState.UserInfo?): AppState {
        return state.value.copy(userInfo = userInfo)
    }
}
