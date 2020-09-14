package ge.baqar.myapplication.data.remote.service

import ge.baqar.myapplication.data.dto.arch.ResponseBase
import ge.baqar.myapplication.data.dto.auth.LoginRequest
import ge.baqar.myapplication.data.dto.auth.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("authenticate/login")
    suspend fun login(@Body request: LoginRequest): ResponseBase<LoginResponse>
}