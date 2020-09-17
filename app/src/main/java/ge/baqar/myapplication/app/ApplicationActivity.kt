package ge.baqar.myapplication.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.setContent
import ge.baqar.myapplication.data.storage.StoragePrefs
import ge.baqar.myapplication.ui.mixin.MyApplicationTheme
import ge.baqar.myapplication.ui.scenes.auth.AuthScreen
import ge.baqar.myapplication.ui.scenes.auth.login.LoginModel
import ge.baqar.myapplication.ui.scenes.auth.login.LoginScreen
import ge.baqar.myapplication.ui.scenes.auth.state.AppAction
import ge.baqar.myapplication.ui.scenes.dashboard.DashboardScreen
import ge.baqar.myapplication.ui.scenes.dashboard.state.DashboardAction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject

@ExperimentalCoroutinesApi
class ApplicationActivity : AppCompatActivity() {

    private val store: Store by inject()
    private val applicationDataStorage: StoragePrefs by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val savedUserInfo = applicationDataStorage.getUserInfo()?.asUserInfo()
        store.newIntent(AppAction.SetUserInfo(savedUserInfo))
        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val state = store.state.collectAsState().value
                    Column {
                        if (state.userInfo != null) {
                            DashboardScreen(state.userInfo) {
                                store.newIntent(AppAction.SetInitialStateAction)
                            }
                        } else {
                            AuthScreen {
                                val loginModel = mutableStateOf(
                                    LoginModel(
                                        mutableStateOf("b.gogia94@gmail.com"),
                                        mutableStateOf("me280820")
                                    )
                                )
                                LoginScreen(loginModel.value) { email, password ->
                                    store.newIntent(AppAction.LoginAction(email, password))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}