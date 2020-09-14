package ge.baqar.myapplication.data.dto.arch

import com.google.gson.annotations.SerializedName

class ResponseBase<T> (
    @field:SerializedName("errorMessage")
    var errorMessage: String? = null,
    @field:SerializedName("token")
    var token: String? = null,
    @field:SerializedName("tokenToDate")
    var tokenToDate: String? = null,
    @field:SerializedName("sessionExpired")
    var sessionExpired: Boolean? = null,
    @field:SerializedName("tokenDate")
    var tokenDate: String? = null,
    @field:SerializedName("personIsLimited")
    var personIsLimited: Boolean? = null,
    @field:SerializedName("limitedMessage")
    var limitedMessage: String? = null,
    @field:SerializedName("value")
    var value: T? = null
)
