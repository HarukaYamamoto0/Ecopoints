package com.economix.ecopoints.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.ImageView
import android.app.Activity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.economix.ecopoints.adapter.OnBoardingViewPagerAdapter
import com.economix.ecopoints.model.OnBoardingData
import com.economix.ecopoints.ui.activities.MainActivity
import com.economix.ecopoints.R

class OnBoardingActivity : Activity() {

    private lateinit var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var onBoardingViewPager: ViewPager
	private lateinit var btnSkipSlide: TextView
	private lateinit var btnPreviousSlide: ImageView
    private lateinit var btnNextSlide: ImageView
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        if (readFirstTimePreference()) return finishOnBoarding()

        initializeViews()

        val onBoardingData = createOnBoardingDataList()

        setOnBoardingViewPagerAdapter(onBoardingData)
        setupListeners(onBoardingData)
    }

    private fun initializeViews() {
        tabLayout = findViewById(R.id.tabLayout)
        onBoardingViewPager = findViewById(R.id.screenPager)
		btnSkipSlide = findViewById(R.id.btn_skip_slide)
		btnPreviousSlide = findViewById(R.id.btn_previous_slide)
        btnNextSlide = findViewById(R.id.btn_next_slide)
    }

    private fun createOnBoardingDataList(): List<OnBoardingData> {
        return listOf(
            OnBoardingData(getString(R.string.title1), getString(R.string.description1), R.drawable.ic_gift),
            OnBoardingData(getString(R.string.title2), getString(R.string.description2), R.drawable.ic_coupon),
            OnBoardingData(getString(R.string.title3), getString(R.string.description2), R.drawable.ic_airplane)
        )
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>) {
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        onBoardingViewPager.adapter = onBoardingViewPagerAdapter
        tabLayout.setupWithViewPager(onBoardingViewPager)
    }

    private fun setupListeners(onBoardingData: List<OnBoardingData>) {
        btnNextSlide.setOnClickListener {
            if (position < onBoardingData.size) {
                position++
                onBoardingViewPager.currentItem = position
            }
			if (position == onBoardingData.size) finishOnBoarding()
        }
		
		btnSkipSlide.setOnClickListener {
			finishOnBoarding()
		}
		
		btnPreviousSlide.setOnClickListener {
			if (position > 0) {
				position--
				onBoardingViewPager.currentItem = position
			}
        }

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position
				
				if (tab.position == onBoardingData.size - 1) {
                    btnNextSlide.setImageResource(R.drawable.ic_play)
                } else {
                    btnNextSlide.setImageResource(R.drawable.ic_arrow_forward)
                }
				
				if (tab.position == 0) {
					btnPreviousSlide.setVisibility(View.INVISIBLE)
				} else {
					btnPreviousSlide.setVisibility(View.VISIBLE)
				}
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Não é necessário implementar neste caso
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Não é necessário implementar neste caso
            }
        })
    }

    private fun saveFirstTimePreference() {
        val preferences: SharedPreferences = applicationContext.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putBoolean("firstTime", true)
        editor.apply()
    }

    private fun readFirstTimePreference(): Boolean {
        val preferences: SharedPreferences = applicationContext.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        return preferences.getBoolean("firstTime", false)
    }
	
	private fun finishOnBoarding() {
		val intent: Intent = Intent(this@OnBoardingActivity, MainActivity::class.java)
		saveFirstTimePreference()
		startActivity(intent)
		finish()
	}
}