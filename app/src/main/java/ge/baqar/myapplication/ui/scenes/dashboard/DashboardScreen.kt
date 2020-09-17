package ge.baqar.myapplication.ui.scenes.dashboard

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ge.baqar.myapplication.ui.scenes.auth.state.AppState
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Composable
fun DashboardScreen(userInfo: AppState.UserInfo?, logOut: () -> Unit) {
    userInfo?.let {
        ScrollableColumn(
            modifier = (Modifier.background(color = Color.White) then Modifier.fillMaxWidth() then Modifier.fillMaxHeight()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Stack(modifier = Modifier.background(Color.Magenta)) {
                Text(text = "${userInfo.firstName} ${userInfo.lastName}")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                logOut()
            }) {
                Text(text = "Log out")
            }
        }
    }
}