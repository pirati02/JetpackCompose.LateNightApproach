package ge.baqar.myapplication.ui.scenes.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import ge.baqar.myapplication.ui.scenes.auth.login.LoginScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.KoinContextHandler

@ExperimentalCoroutinesApi
@Composable
fun AuthScreen() {
    val viewModel = remember {
        KoinContextHandler.get().get<AuthViewModel>()
    }
    //Todo: Check Auth view state for navigation
    LoginScreen()
}