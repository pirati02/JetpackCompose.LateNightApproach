package ge.baqar.myapplication.data.storage

import android.app.Application
import android.preference.PreferenceManager
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.java.KoinJavaComponent.inject

@ExperimentalCoroutinesApi
class StoragePrefs {

    companion object {
        private val application: Application by inject(Application::class.java)

        private val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(application.applicationContext)

        var scheme: String?
            get() {
                return sharedPreferences.getString("scheme", "")
            }
            set(value) {
                sharedPreferences.edit().putString("scheme", value).apply()
            }

        var userName: String?
            get() {
                return sharedPreferences.getString("userName", "")
            }
            set(value) {
                sharedPreferences.edit().putString("userName", value).apply()
            }

        var notificationId: String?
            get() {
                return sharedPreferences.getString("notificationId", "")
            }
            set(value) {
                sharedPreferences.edit().putString("notificationId", value).apply()
            }

        var osName: String?
            get() {
                return sharedPreferences.getString("osName", "")
            }
            set(value) {
                sharedPreferences.edit().putString("osName", value).apply()
            }

        var appVersion: String?
            get() {
                return sharedPreferences.getString("appVersion", "")
            }
            set(value) {
                sharedPreferences.edit().putString("appVersion", value).apply()
            }

        var userInfo: String?
            get() {
                return sharedPreferences.getString("userInfo", "")
            }
            set(value) {
                sharedPreferences.edit().putString("userInfo", value).apply()
            }


        fun initDefaults() {
            osName = "android"
            appVersion = "2.3.3"
        }

        fun setUserInfo(
            userId: Int? = null,
            token: String? = null,
            firstName: String? = null,
            lastName: String? = null,
            mobile: String? = null,
            personalNumber: String? = null,
            personId: Int? = null,
            accountActivated: Boolean? = null,
            birthDate: String? = null,
            email: String? = null,
            personIsLimited: Boolean? = null,
            isSuperUser: Boolean? = null
        ) {
            userInfo = UserStorageInfo(
                userId,
                token,
                firstName,
                lastName,
                mobile,
                personalNumber,
                personId,
                accountActivated,
                birthDate,
                email,
                personIsLimited,
                isSuperUser
            ).toString()
        }

        fun getUserInfo(): UserStorageInfo {
            return UserStorageInfo().fromJson(userInfo!!)
        }
    }

    data class UserStorageInfo(
        val userId: Int? = null,
        val token: String? = null,
        val firstName: String? = null,
        val lastName: String? = null,
        val mobile: String? = null,
        val personalNumber: String? = null,
        val personId: Int? = null,
        val accountActivated: Boolean? = null,
        val birthDate: String? = null,
        val email: String? = null,
        val personIsLimited: Boolean? = null,
        val isSuperUser: Boolean? = null
    ) {
        override fun toString(): String {
            return Gson().toJson(this)
        }

        fun fromJson(json: String): UserStorageInfo {
            return Gson().fromJson(json, UserStorageInfo::class.java)
        }
    }
}