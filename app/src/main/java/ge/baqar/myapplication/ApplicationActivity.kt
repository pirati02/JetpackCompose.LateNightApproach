package ge.baqar.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import ge.baqar.myapplication.data.storage.StoragePrefs
import ge.baqar.myapplication.ui.mixin.MyApplicationTheme
import ge.baqar.myapplication.ui.scenes.auth.AuthScreen
import ge.baqar.myapplication.ui.scenes.auth.login.LoginViewModel
import ge.baqar.myapplication.ui.scenes.auth.state.SetUserInfo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject

@ExperimentalCoroutinesApi
class ApplicationActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by inject()
    private val applicationDataStorage: StoragePrefs by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val savedUserInfo = applicationDataStorage.getUserInfo()?.asUserInfo();
        loginViewModel.newIntent(SetUserInfo(savedUserInfo))
        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val state = loginViewModel.state?.collectAsState()?.value
                    Column {
                        if (state != null) {
                            ScrollableColumn(
                                modifier = (Modifier.background(color = Color.White) then Modifier.fillMaxWidth() then Modifier.fillMaxHeight()),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "it's dashboard")
                            }
                        } else {
                            AuthScreen()
                        }
                    }
                }
            }
        }
    }
}