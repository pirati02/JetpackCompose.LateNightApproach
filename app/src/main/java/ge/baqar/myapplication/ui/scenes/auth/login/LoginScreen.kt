package ge.baqar.myapplication.ui.scenes.auth.login

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import ge.baqar.myapplication.ui.scenes.auth.state.LoginAction
import ge.baqar.myapplication.ui.scenes.auth.state.SetInitialStateAction
import ge.baqar.myapplication.ui.scenes.auth.state.StoreUserInfoAction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.KoinContextHandler.get

@ExperimentalCoroutinesApi
@Composable
fun LoginScreen() {
    val viewModel = remember {
        get().get<LoginViewModel>()
    }

    val state = viewModel.state?.collectAsState()?.value
    state?.let {
        Column(modifier = Modifier.background(Color.Blue)) {
            if (state.isInProgress) {
                CircularProgressIndicator(progress = 0.5f)
            } else {
                when {
                    state.error != null -> {
                        Column {
                            Stack(modifier = Modifier.background(Color.Magenta)) {
                                Text(text = state.error)
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(onClick = {
                                viewModel.newIntent(SetInitialStateAction)
                            }) {
                                Text(text = "Try Again")
                            }
                        }
                    }
                    state.userInfo != null -> {
                        viewModel.newIntent(StoreUserInfoAction(state.userInfo))
                    }
                    else -> {
                        val loginModel by remember {
                            mutableStateOf(
                                LoginModel(
                                    mutableStateOf("b.gogia94@gmail.com"),
                                    mutableStateOf("me280820")
                                )
                            )
                        }
                        ScrollableColumn(
                            modifier = (Modifier.background(color = Color.White) then Modifier.fillMaxWidth() then Modifier.fillMaxHeight()),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            OutlinedTextField(
                                value = loginModel.email.value,
                                onValueChange = { loginModel.email.value = it },
                                keyboardType = KeyboardType.Email,
                                label = {
                                    Text(text = "Email")
                                })
                            Spacer(modifier = Modifier.preferredHeight(10.dp))
                            OutlinedTextField(
                                value = loginModel.password.value,
                                onValueChange = { loginModel.password.value = it },
                                label = {
                                    Text(text = "Password")
                                }
                            )

                            Spacer(modifier = Modifier.preferredHeight(15.dp))
                            Button(
                                onClick = {
                                    viewModel.newIntent(
                                        LoginAction(
                                            loginModel.email.value,
                                            loginModel.password.value
                                        )
                                    )
                                },
                                backgroundColor = Color.Cyan,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    "Log in",
                                    style = MaterialTheme.typography.body1,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
