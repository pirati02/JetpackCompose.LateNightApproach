package ge.baqar.myapplication.ui.scenes.auth.state

sealed class AuthEffect : AuthResult() {
    data class Login(val url: String) : AuthEffect()
    data class Register(val message: String) : AuthEffect()
}