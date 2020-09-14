package com.github.brewin.mvicoroutines.utility

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.ContextAmbient
import kotlinx.coroutines.ExperimentalCoroutinesApi

class NetworkStatus (private val application: Application) {

    @ExperimentalCoroutinesApi
    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(): Boolean {
        val context: Context = application.applicationContext!!
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                }
            }
        }
        return false
    }
}