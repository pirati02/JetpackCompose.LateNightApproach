package ge.baqar.myapplication.ui.scene

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class LoginModel(
    val userName: MutableState<String> = mutableStateOf(""),
    val password: MutableState<String> = mutableStateOf("")
)
