package com.economix.ecopoints.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.economix.ecopoints.R
import android.widget.RelativeLayout
import com.economix.ecopoints.ui.activities.SettingsActivity

class ExtractActivity : AppCompatActivity() {
	
	private lateinit var toolBarLayout: RelativeLayout
	private lateinit var btnSettings: ImageButton
	
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extract)
		
		toolBarLayout = findViewById(R.id.layout_toolbar)
		btnSettings = toolBarLayout.findViewById(R.id.btn_settings)
		
		btnSettings.setOnClickListener {
			startActivity(Intent(this@ExtractActivity, SettingsActivity::class.java))
		}
    }
}
