package ge.baqar.myapplication.ui.scenes.auth.state

open class AuthResult

data class AuthState(
    val isInProgress: Boolean,
    val userInfo: UserInfo?,
    val error: String?
) : AuthResult() {

    companion object {
        val DEFAULT = AuthState(
            isInProgress = false,
            error = null,
            userInfo = null
        )
    }
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
)