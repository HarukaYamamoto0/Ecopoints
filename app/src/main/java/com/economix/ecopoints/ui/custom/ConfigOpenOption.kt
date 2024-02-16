package com.economix.ecopoints.ui.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.economix.ecopoints.R

class ConfigOpenOption @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val iconImageView: ImageView
    private val titleTextView: TextView
    private val subtitleTextView: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_config_open_option, this, true)

        iconImageView = findViewById(R.id.imageview_open_option_icon)
        titleTextView = findViewById(R.id.textView_open_option_title)
        subtitleTextView = findViewById(R.id.textView_open_option_subtitle)
        
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ConfigOptionSwitch)

        val iconResId = typedArray.getResourceId(R.styleable.ConfigOpenOption_icon, -1)
        val iconColor = typedArray.getColor(R.styleable.ConfigOpenOption_iconColor, -1)

        setIcon(AppCompatResources.getDrawable(context, iconResId), iconColor)

        setTitle(typedArray.getString(R.styleable.ConfigOpenOption_title) ?: "title")
        setSubtitle(typedArray.getString(R.styleable.ConfigOpenOption_subtitle) ?: "subtitle")

        typedArray.recycle()
    }

    private fun setIcon(icon: Drawable?, color: Int) {
        icon?.let {
			if (color != -1) {
				val coloredIcon = it.mutate()
				coloredIcon.setTint(color)
				iconImageView.setImageDrawable(coloredIcon)
			} else {
				iconImageView.setImageDrawable(icon)
			}
        }
    }

    fun setTitle(title: String) {
        titleTextView.text = title
    }

    fun setSubtitle(subtitle: String) {
        subtitleTextView.text = subtitle
    }
}
