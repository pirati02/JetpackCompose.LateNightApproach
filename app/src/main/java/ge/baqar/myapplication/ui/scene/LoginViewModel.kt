package ge.baqar.myapplication.ui.scene

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class LoginViewModel : ViewModel() {
    suspend fun doLogin(model: LoginModel) {
        val result = viewModelScope.async {
            delay(2500)
            println("${model.userName.value} and ${model.password.value}")
            delay(2500)
            "success"
        }.await()

        println(result)
    }
}