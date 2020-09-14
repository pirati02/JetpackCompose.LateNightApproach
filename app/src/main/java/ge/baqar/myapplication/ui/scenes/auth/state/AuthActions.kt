package ge.baqar.myapplication.ui.scenes.auth.state



sealed class AuthAction
data class LoginAction(var email: String?, var password: String?) : AuthAction(){
    constructor() : this(null, null)
}
data class RegisterAction(val email: String) : AuthAction()