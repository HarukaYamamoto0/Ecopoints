package com.economix.ecopoints.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.economix.ecopoints.R
import com.economix.ecopoints.ui.custom.ConfigOptionSwitch
import com.economix.ecopoints.ui.custom.ConfigOpenOption
import com.economix.ecopoints.utils.changeTouchableAreaOfView
import com.economix.ecopoints.utils.SharedPreferencesManager

class SettingsActivity : AppCompatActivity() {

    private lateinit var btnArrowBack: ImageView
    private lateinit var imageViewAvatar: ImageView
    private lateinit var textViewUsername: TextView
    private lateinit var textViewCPF: TextView
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    // Options
    private lateinit var darkModeOption: ConfigOptionSwitch
    private lateinit var notificationsOption: ConfigOptionSwitch
    private lateinit var locationAccessOption: ConfigOptionSwitch
    private lateinit var themeOption: ConfigOpenOption
    private lateinit var languageOption: ConfigOpenOption
    private lateinit var syncDataOption: ConfigOpenOption
    private lateinit var aboutProjectOption: ConfigOpenOption
    private lateinit var aboutDevOption: ConfigOpenOption
    private lateinit var aboutAppOption: ConfigOpenOption

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        sharedPreferencesManager = SharedPreferencesManager.getInstance(this)

        initializeViews()
        setClickListeners()
    }

    private fun initializeViews() {
        btnArrowBack = findViewById(R.id.btn_arrow_back_settings)
        changeTouchableAreaOfView(btnArrowBack, 5)
        imageViewAvatar = findViewById(R.id.imageView_avatar_settings)
        textViewUsername = findViewById(R.id.textView_username_settings)
        textViewCPF = findViewById(R.id.textView_cpf_settings)

        loadUserData()

        darkModeOption = findViewById(R.id.config_option_switch_dark_mode)
        notificationsOption = findViewById(R.id.config_option_switch_notifications)
        locationAccessOption = findViewById(R.id.config_option_switch_location_access)
        themeOption = findViewById(R.id.config_option_switch_theme)
        languageOption = findViewById(R.id.config_option_switch_language)
        syncDataOption = findViewById(R.id.config_option_switch_sync_data)
        aboutProjectOption = findViewById(R.id.config_option_switch_about_project)
        aboutDevOption = findViewById(R.id.config_option_switch_about_dev)
        aboutAppOption = findViewById(R.id.config_option_switch_about_app)

        // Define os ouvintes de estado do switch
        setSwitchListeners()
    }

    private fun loadUserData() {
        val avatarUrl = sharedPreferencesManager.getString("avatarUrl", "https://imgur.com/WRQ2Kbj.png")
        Glide.with(this@SettingsActivity)
            .load(avatarUrl)
            .into(imageViewAvatar)

        val username = sharedPreferencesManager.getString("username", "Antonio Albert")
        textViewUsername.text = "$username M C"

        val cpf = sharedPreferencesManager.getString("cpf", "137.400.773-01")
        textViewCPF.text = cpf
    }

    private fun setClickListeners() {
        btnArrowBack.setOnClickListener {
            finish()
        }

        themeOption.setOnClickListener {
            showToast(getString(R.string.theme))
        }

        languageOption.setOnClickListener {
            showToast(getString(R.string.language))
        }

        syncDataOption.setOnClickListener {
            showToast(getString(R.string.sync_data))
        }

        aboutProjectOption.setOnClickListener {
            openURL("https://www.tabnews.com.br/HarukaYamamoto0")
        }

        aboutDevOption.setOnClickListener {
            openURL("https://harukayamamoto0.github.io/portfolio/")
        }

        aboutAppOption.setOnClickListener {
            showToast(getString(R.string.about_app))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@SettingsActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun openURL(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            showToast(getString(R.string.no_browsers_found))
        }
    }

    private fun setSwitchListeners() {
        darkModeOption.setOnSwitchCheckedChangeListener { isChecked ->
            darkModeOption.toggle()
        }

        notificationsOption.setOnSwitchCheckedChangeListener { isChecked ->
            notificationsOption.toggle()
        }

        locationAccessOption.setOnSwitchCheckedChangeListener { isChecked ->
            locationAccessOption.toggle()
        }
    }
}
