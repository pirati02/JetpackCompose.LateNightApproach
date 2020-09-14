package ge.baqar.myapplication.data.dto.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("lastName")
	val lastName: String? = null,

	@field:SerializedName("mobile")
	val mobile: String? = null,

	@field:SerializedName("personalNumber")
	val personalNumber: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("lastNameEn")
	val lastNameEn: String? = null,

	@field:SerializedName("birthDate")
	val birthDate: String? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("firstName")
	val firstName: String? = null,

	@field:SerializedName("firstNameEn")
	val firstNameEn: String? = null,

	@field:SerializedName("accountActivated")
	val accountActivated: Boolean? = null,

	@field:SerializedName("personId")
	val personId: Int? = null,

	@field:SerializedName("isSuperUser")
	val isSuperUser: Boolean? = null,

	@field:SerializedName("personIsLimited")
	val personIsLimited: Boolean? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class LoginRequest(
	@field:SerializedName("Email")
	val email: String?,
	@field:SerializedName("Password")
	val password: String?,
	@field:SerializedName("UserId")
	val userId: Int?,
	@field:SerializedName("IsMobile")
	val isMobile: Boolean?,
	@field:SerializedName("NotificationUserID")
	val notificationUserID: String?
) {

	constructor() : this(null, null, null, null, null)

	fun create(
		email: String?,
		password: String?
	): LoginRequest =
		LoginRequest(email, password, null, null, null)

}
