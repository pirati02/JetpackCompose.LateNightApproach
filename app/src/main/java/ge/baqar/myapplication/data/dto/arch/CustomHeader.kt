package ge.baqar.myapplication.data.dto.arch

import com.google.gson.annotations.SerializedName

open class HeaderBaseRequest {
    @field:SerializedName("Header")
    open var headerObject: CustomHeader? = null
}

data class CustomHeader(
    @field:SerializedName("token")
    val token: String,
    @field:SerializedName("scheme")
    var scheme: String,
    @field:SerializedName("userName")
    var userName: String,
    @field:SerializedName("password")
    var password: String,
    @field:SerializedName("tokenDate")
    var tokenDate: String,
    @field:SerializedName("notificationId")
    var notificationId: String,
    @field:SerializedName("osName")
    var osName: String,
    @field:SerializedName("appVersion")
    var appVersion: String
)