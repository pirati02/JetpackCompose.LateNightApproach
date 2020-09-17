package ge.baqar.myapplication.ui.scenes.dashboard

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ge.baqar.myapplication.ui.scenes.auth.state.SetInitialStateAction


@Composable
fun DashboardScreen(state: DashboardState) {
    Column(modifier = Modifier.background(Color.Magenta)) {
        Stack(modifier = Modifier.background(Color.Magenta)) {
            Text(text = "${state.userInfo.firstName} ${state.userInfo.lastName}")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            viewModel.newIntent(SetInitialStateAction)
        }) {
            Text(text = "Log out")
        }
    }
}