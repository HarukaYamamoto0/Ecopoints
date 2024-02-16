package com.economix.ecopoints

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import android.net.NetworkInfo

class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
		val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
		val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (!isConnected) {
            showLongToast(context, R.string.toast_internet_connection_required)
        }
    }

    private fun showLongToast(context: Context, messageResId: Int) {
        val message = context.getString(messageResId)
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
