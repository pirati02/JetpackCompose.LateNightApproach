package ge.baqar.myapplication.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.github.brewin.mvicoroutines.utility.NetworkStatus
import ge.baqar.myapplication.data.dto.auth.LoginRequest
import ge.baqar.myapplication.data.dto.auth.LoginResponse
import ge.baqar.myapplication.data.error.ConnectionError
import ge.baqar.myapplication.data.error.DomainError
import ge.baqar.myapplication.data.remote.service.AuthService
import ge.baqar.myapplication.ui.scenes.arch.ReactiveResult
import ge.baqar.myapplication.ui.scenes.arch.asError
import ge.baqar.myapplication.ui.scenes.arch.asSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException


class AccountRepositoryImpl(
    private val authService: AuthService,
    private val networkStatus: NetworkStatus
) {

    @ExperimentalCoroutinesApi
    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun login(request: LoginRequest): ReactiveResult<DomainError, LoginResponse> =
        coroutineScope {
            if (networkStatus.isOnline()) {
                try {
                    val result = withContext(Dispatchers.Default) {
                        authService.login(request)
                    }
                    return@coroutineScope if (result.value == null)
                        object : DomainError {
                            override val message: String?
                                get() = result.errorMessage
                            override val exception: Exception?
                                get() = Exception(result.errorMessage)
                        }.asError
                    else
                        result.value?.asSuccess!!
                } catch (e: Exception) {
                    Timber.e(e)
                    when (e) {
                        is IOException ->
                            ConnectionError("Error connecting to GitHub", e)
                        else -> throw e
                    }.asError
                }
            } else {
                throw NotImplementedError("Local storage not implemented")
            }
        }
}