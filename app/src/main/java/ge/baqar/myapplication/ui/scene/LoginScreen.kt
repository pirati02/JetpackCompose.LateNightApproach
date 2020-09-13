package ge.baqar.myapplication.ui.scene

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun loginScreen(loginModelState: MutableState<LoginModel> = mutableStateOf(LoginModel()), onLogin: ((String, String) -> Unit)) {
    val loginModel = remember { loginModelState.value }
    ScrollableColumn(
        modifier = Modifier.background(color = Color.White) +
                Modifier.fillMaxWidth() +
                Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        TextField(
            value = loginModel.userName.value,
            onValueChange = { loginModel.userName.value = it },
            label = {
                Text(text = "User name")
            })
        Spacer(modifier = Modifier.preferredHeight(10.dp))
        TextField(
            value = loginModel.password.value,
            onValueChange = { loginModel.password.value = it },
            label = {
                Text(text = "Password")
            })

        Spacer(modifier = Modifier.preferredHeight(15.dp))
        Button(
            onClick = { onLogin.invoke(loginModel.userName.value, loginModel.password.value) },
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