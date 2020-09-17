package ge.baqar.myapplication.ui.scenes.auth.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class LoginModel(
    var email: MutableState<String> = mutableStateOf(""),
    var password: MutableState<String> = mutableStateOf("")
)
