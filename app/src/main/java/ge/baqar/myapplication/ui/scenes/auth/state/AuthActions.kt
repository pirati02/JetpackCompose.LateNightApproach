package ge.baqar.myapplication.ui.scenes.auth.state



sealed class AuthAction
class SetInitialStateAction: AuthAction()
data class LoginAction(var email: String?, var password: String?) : AuthAction()
data class RegisterAction(val email: String) : AuthAction()