package ge.baqar.myapplication.ui.scenes.auth.state

import com.google.gson.Gson
import ge.baqar.myapplication.data.storage.UserStorageInfo
import ge.baqar.myapplication.ui.scenes.dashboard.state.DashboardAction
import kotlinx.coroutines.ExperimentalCoroutinesApi

//Actions
sealed class AppAction {
    data class SetUserInfo(var userInfo: AppState.UserInfo?) : AppAction()
    object SetInitialStateAction : AppAction()
    data class LoginAction(var email: String?, var password: String?) : AppAction()
    data class StoreUserInfoAction(val userInfo: AppState.UserInfo?) : AppAction()
}

//State
data class AppState(
    val isInProgress: Boolean,
    val userInfo: UserInfo?,
    val error: String?
) {

    companion object {
        val DEFAULT = AppState(
            isInProgress = false,
            error = null,
            userInfo = null
        )
    }

    data class UserInfo(
        val userId: Int? = null,
        val token: String? = null,
        val firstName: String? = null,
        val lastName: String? = null,
        val mobile: String? = null,
        val personalNumber: String? = null,
        val personId: Int? = null,
        val accountActivated: Boolean? = null,
        val birthDate: String? = null,
        val email: String? = null,
        val personIsLimited: Boolean? = null,
        val isSuperUser: Boolean? = null
    ) {
        @ExperimentalCoroutinesApi
        fun asStorage(): UserStorageInfo {
            return Gson().fromJson(Gson().toJson(this), UserStorageInfo::class.java)
        }
    }
}