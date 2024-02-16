package com.economix.ecopoints.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.economix.ecopoints.model.OnBoardingData
import com.economix.ecopoints.R

class OnBoardingViewPagerAdapter(
    private val context: Context,
    private val onBoardingDataList: List<OnBoardingData>
) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return onBoardingDataList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(context).inflate(R.layout.onboarding_screen_layout, container, false)

        val imageView: ImageView = view.findViewById(R.id.imageview_onboarding_icon)
        val title: TextView = view.findViewById(R.id.textview_onboarding_title)
        val description: TextView = view.findViewById(R.id.textview_onboarding_description)

        imageView.setImageResource(onBoardingDataList[position].imageURL)
        title.text = onBoardingDataList[position].title
        description.text = onBoardingDataList[position].description

        container.addView(view)
        return view
    }
}