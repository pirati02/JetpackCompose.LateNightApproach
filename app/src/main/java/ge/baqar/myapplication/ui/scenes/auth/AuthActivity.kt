package ge.baqar.myapplication.ui.scenes.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import ge.baqar.myapplication.ui.mixin.MyApplicationTheme
import ge.baqar.myapplication.ui.scenes.auth.login.LoginModel
import ge.baqar.myapplication.ui.scenes.auth.login.LoginViewModel
import ge.baqar.myapplication.ui.scenes.auth.login.loginScreen
import ge.baqar.myapplication.ui.scenes.auth.state.AuthAction
import ge.baqar.myapplication.ui.scenes.auth.state.AuthState
import ge.baqar.myapplication.ui.scenes.auth.state.LoginAction
import ge.baqar.myapplication.ui.scenes.auth.state.SetInitialStateAction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@ExperimentalCoroutinesApi
@FlowPreview
class AuthActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    renderState()
                }
            }
        }
    }

    @Composable
    fun renderState() {
        val state = viewModel.state.collectAsState().value
        Column(modifier = Modifier.background(Color.Blue)) {
            if (state.isInProgress) {
                CircularProgressIndicator(progress = 0.5f)
            } else {
                if (state.error != null) {
                    Column {
                        Stack(modifier = Modifier.background(Color.Magenta)) {
                            Text(text = state.error)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = {
                            viewModel.newIntent(SetInitialStateAction())
                        }) {
                            Text(text = "Try Again")
                        }
                    }
                } else if (state.userInfo != null) {
                    Column(modifier = Modifier.background(Color.Magenta)) {
                        Stack(modifier = Modifier.background(Color.Magenta)) {
                            Text(text = "${state.userInfo.firstName} ${state.userInfo.lastName}")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = {
                            viewModel.newIntent(SetInitialStateAction())
                        }) {
                            Text(text = "Log out")
                        }
                    }
                } else {
                    val loginModel = mutableStateOf(
                        LoginModel(
                            mutableStateOf("b.gogia94@gmail.com"),
                            mutableStateOf("me280820")
                        )
                    )
                    loginScreen(loginModel) { email, password ->
                        viewModel.newIntent(LoginAction(email, password))
                    }
                }
            }
        }
    }
}