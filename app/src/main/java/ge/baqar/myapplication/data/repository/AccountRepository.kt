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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import java.io.IOException


class AccountRepositoryImpl(val authService: AuthService, val networkStatus: NetworkStatus) {

    @ExperimentalCoroutinesApi
    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun login(request: LoginRequest): ReactiveResult<DomainError, LoginResponse> =
        coroutineScope {
            if (networkStatus.isOnline()) {
                try {
                    val result = async { authService.login(request) }.await()
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