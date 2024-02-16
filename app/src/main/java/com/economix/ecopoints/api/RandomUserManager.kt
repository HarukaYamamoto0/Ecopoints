package com.economix.ecopoints.api

import android.os.AsyncTask
import com.economix.ecopoints.model.UserData
import com.economix.ecopoints.utils.CPFUtils
import com.economix.ecopoints.utils.RandomNumberGenerator
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class RandomUserManager(private val listener: OnUserDataFetchedListener) {

    fun getRandomUserData() {
        RandomUserDataTask().execute()
    }

    interface OnUserDataFetchedListener {
        fun onUserDataFetched(userData: UserData)
        fun onDataFetchError(exception: Exception)
    }

    private inner class RandomUserDataTask : AsyncTask<Void, Void, UserData?>() {

        private val client = OkHttpClient()

        override fun doInBackground(vararg params: Void?): UserData? {
            val request = Request.Builder()
                .url("https://randomuser.me/api/")
                .build()

            return try {
                val response = client.newCall(request).execute()
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val responseBody = response.body?.string()
                val jsonObject = JSONObject(responseBody)
                val results = jsonObject.getJSONArray("results")
                val user = results.getJSONObject(0)

                val firstName = user.getJSONObject("name").getString("first")
                val lastName = user.getJSONObject("name").getString("last")
                val avatarUrl = user.getJSONObject("picture").getString("large")
                val cpf = CPFUtils.generateRandomCPF()
                val wallet = RandomNumberGenerator.generateRandomNumber(0, 9000)

                UserData(firstName, lastName, cpf, avatarUrl, wallet)
            } catch (e: Exception) {
                null
            }
        }

        override fun onPostExecute(result: UserData?) {
            super.onPostExecute(result)
            result?.let { listener.onUserDataFetched(it) }
                ?: listener.onDataFetchError(IOException("Failed to fetch user data"))
        }
    }
}
