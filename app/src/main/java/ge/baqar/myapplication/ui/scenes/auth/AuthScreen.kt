package ge.baqar.myapplication.ui.scenes.auth

import androidx.compose.runtime.Composable
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun AuthScreen(
    loginScreen: @Composable () -> Unit
) {
//    val viewModel = remember {
//        KoinContextHandler.get().get<AuthViewModel>()
//    }
    //Todo: Check Auth view state for navigation
    loginScreen()
}