package ge.baqar.myapplication.ui.scenes.auth.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class LoginModel(
    val email: MutableState<String> = mutableStateOf(""),
    val password: MutableState<String> = mutableStateOf("")
)
