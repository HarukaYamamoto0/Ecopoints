package com.economix.ecopoints.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.economix.ecopoints.R
import com.economix.ecopoints.adapter.ExchangeOptionAdapter
import com.economix.ecopoints.provider.ExchangeOptionsProvider
import com.economix.ecopoints.utils.SharedPreferencesManager
import com.economix.ecopoints.utils.changeTouchableAreaOfView
import com.economix.ecopoints.ui.activities.SignInActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textViewPoints: TextView
    private lateinit var btnViewExtract: TextView
	private lateinit var toolBarLayout: RelativeLayout
    private lateinit var btnSettings: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ExchangeOptionAdapter
    private lateinit var sharedPreferencesManager: SharedPreferencesManager
    private var wallet: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        sharedPreferencesManager = SharedPreferencesManager.getInstance(this)

        getUserData()
        initializeViews()
        setupRecycler()
        checkLoginStatus()
    }

    private fun getUserData() {
        wallet = sharedPreferencesManager.getInt("wallet", 0)
    }

    private fun initializeViews() {
        textViewPoints = findViewById(R.id.textview_points)
        btnViewExtract = findViewById(R.id.textview_extract)
		toolBarLayout = findViewById(R.id.layout_toolbar)
        btnSettings = toolBarLayout.findViewById(R.id.btn_settings)
        recyclerView = findViewById(R.id.layout_exchange_options)

        setupClickListeners()
        textViewPoints.text = wallet.toString()
    }

    private fun setupClickListeners() {
        changeTouchableAreaOfView(btnViewExtract, 8)
        changeTouchableAreaOfView(btnSettings, 5)

        btnSettings.setOnClickListener {
            startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
        }

        btnViewExtract.setOnClickListener {
            startActivity(Intent(this@MainActivity, ExtractActivity::class.java))
        }
    }

    private fun setupRecycler() {
        val exchangeOptions = ExchangeOptionsProvider.getExchangeOptions(this)

        adapter = ExchangeOptionAdapter(this, exchangeOptions, supportFragmentManager)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 3)
    }

    private fun checkLoginStatus() {
        if (!sharedPreferencesManager.getBoolean("logged", false)) {
            startActivity(Intent(this@MainActivity, SignInActivity::class.java))
            finish()
        }
    }
}
