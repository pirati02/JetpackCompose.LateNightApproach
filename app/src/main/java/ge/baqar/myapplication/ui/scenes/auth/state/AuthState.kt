package ge.baqar.myapplication.ui.scenes.auth.state

import com.google.gson.Gson
import ge.baqar.myapplication.data.storage.StoragePrefs
import ge.baqar.myapplication.data.storage.UserStorageInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi

//Actions
sealed class AuthAction
object SetInitialStateAction : AuthAction()
data class LoginAction(var email: String?, var password: String?) : AuthAction()
data class StoreUserInfoAction(val userInfo: UserState.UserInfo?) : AuthAction()
data class SetUserInfo(var userInfo: UserState.UserInfo?) : AuthAction()
data class RegisterAction(val email: String) : AuthAction()

//State
sealed class AuthState
data class UserState(
    val isInProgress: Boolean,
    val userInfo: UserInfo?,
    val error: String?
) : AuthState() {

    companion object {
        val DEFAULT = UserState(
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