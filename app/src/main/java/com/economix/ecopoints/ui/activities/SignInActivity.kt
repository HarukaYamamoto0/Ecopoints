package com.economix.ecopoints.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.economix.ecopoints.R
import com.economix.ecopoints.api.RandomUserManager
import com.economix.ecopoints.model.UserData
import com.economix.ecopoints.utils.SharedPreferencesManager
import com.google.android.material.textfield.TextInputEditText
import android.widget.Button
import com.economix.ecopoints.ui.activities.MainActivity

class SignInActivity : AppCompatActivity(), RandomUserManager.OnUserDataFetchedListener {

    private lateinit var edittextCpf: TextInputEditText
    private lateinit var edittextPassword: TextInputEditText
    private lateinit var btnLogin: Button
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        edittextCpf = findViewById(R.id.edittext_cpf)
        edittextPassword = findViewById(R.id.edittext_password)
        btnLogin = findViewById(R.id.btn_login)

        sharedPreferencesManager = SharedPreferencesManager.getInstance(this)

        btnLogin.setOnClickListener {
            if (checkCredentials()) {
                registerLogin()
                createRandomUser()
            } else {
                Toast.makeText(this, "Senha errada!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkCredentials(): Boolean {
        return (edittextCpf.text.toString() == "0000" && edittextPassword.text.toString() == "0000")
    }

    private fun registerLogin() {
        sharedPreferencesManager.setBoolean("logged", true)
    }

    private fun createRandomUser() {
        val userManager = RandomUserManager(this)
        userManager.getRandomUserData()
    }

    override fun onUserDataFetched(userData: UserData) {
        sharedPreferencesManager.setString("username", "${userData.firstName} ${userData.lastName}")
        sharedPreferencesManager.setString("cpf", userData.cpf)
        sharedPreferencesManager.setString("avatarUrl", userData.avatarUrl)
        sharedPreferencesManager.setInt("wallet", userData.wallet)

        Toast.makeText(this, "Agora você está logado com sucesso", Toast.LENGTH_SHORT).show()

        val intentSettings = Intent(this@SignInActivity, MainActivity::class.java)
        startActivity(intentSettings)
        finish()
    }

    override fun onDataFetchError(exception: Exception) {
        Toast.makeText(this, "Erro ao criar usuário: ${exception.message}", Toast.LENGTH_SHORT).show()
    }
}
