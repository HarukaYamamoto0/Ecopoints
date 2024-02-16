package com.economix.ecopoints.ui.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.economix.ecopoints.R
import androidx.core.content.ContextCompat

class ExchangeOptionCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val iconView: ImageView
    private val textView: TextView
    private var cardDrawable: Drawable
    private var descriptionText: String

    init {
        LayoutInflater.from(context).inflate(R.layout.exchange_option_layout, this, true)

        iconView = findViewById(R.id.imageview_icon)
        textView = findViewById(R.id.textview_text)

        isClickable = true
		
		cardDrawable = context.getDrawable(R.drawable.ic_gift)!!
        descriptionText = "Default"
    }

    fun setIcon(iconDrawable: Int) {
        iconView.setImageDrawable(getDrawableFromResources(iconDrawable))
    }
	
	fun getIcon(): Drawable {
		return iconView.drawable
	}

    fun setText(text: String) {
        textView.text = text
    }
	
	fun getText(): String {
		return textView.text.toString()
	}

    fun setCard(cardDrawable: Int) {
        this.cardDrawable = getDrawableFromResources(cardDrawable)!!
    }
	
	fun getCard(): Drawable {
		return cardDrawable
	}

    fun setDescription(descriptionText: String) {
        this.descriptionText = descriptionText
    }
	
	fun getDescription(): String {
		return descriptionText
	}
	
	private fun getDrawableFromResources(resourceId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resourceId)
    }
}
