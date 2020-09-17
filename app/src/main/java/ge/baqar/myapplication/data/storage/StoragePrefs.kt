package ge.baqar.myapplication.data.storage

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import ge.baqar.myapplication.ui.scenes.auth.state.AppState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.java.KoinJavaComponent.inject

@ExperimentalCoroutinesApi
class StoragePrefs {

    private val application: Application by inject(Application::class.java)

    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(application.applicationContext)
        initDefaults()
    }

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

    fun setUserInfo(userinfo: UserStorageInfo) {
        this.userInfo = userinfo.toString()
    }

    fun getUserInfo(): UserStorageInfo? {
        return if (userInfo != null) UserStorageInfo().fromJson(userInfo!!) else null
    }

    private fun initDefaults() {
        osName = "android"
        appVersion = "2.3.3"
    }

    fun clearUserInfo() {
        userInfo = null
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

    fun fromJson(json: String?): UserStorageInfo? {
        return if (json != null) Gson().fromJson(json, UserStorageInfo::class.java) else null
    }

    fun asUserInfo(): AppState.UserInfo? {
        return AppState.UserInfo(
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
        )
    }
}