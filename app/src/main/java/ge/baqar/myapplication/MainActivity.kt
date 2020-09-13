package ge.baqar.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.state
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.ui.tooling.preview.Preview
import ge.baqar.myapplication.ui.mixin.MyApplicationTheme
import ge.baqar.myapplication.ui.scene.LoginModel
import ge.baqar.myapplication.ui.scene.LoginViewModel
import ge.baqar.myapplication.ui.scene.loginScreen
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider.NewInstanceFactory().create(LoginViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val loginModel = mutableStateOf(LoginModel())
                    loginScreen(loginModel) { userName, password ->
                        lifecycleScope.launch {
                            viewModel.doLogin(loginModel.value)
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            val loginModel = mutableStateOf(LoginModel())
            loginScreen(loginModel) { userName, password ->
                println("${userName} and ${password}")
            }
        }
    }
}