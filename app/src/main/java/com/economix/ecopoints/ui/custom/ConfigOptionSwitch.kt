package com.economix.ecopoints.ui.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.content.res.AppCompatResources
import com.economix.ecopoints.R
import android.widget.TextView

class ConfigOptionSwitch @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val iconImageView: ImageView
    private val titleTextView: TextView
    private val subtitleTextView: TextView
    private val switchCompat: SwitchCompat

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_config_option_switch, this, true)

        iconImageView = findViewById(R.id.imageview_option_night_mode)
        titleTextView = findViewById(R.id.textView_option_title)
        subtitleTextView = findViewById(R.id.textView_option_subtitle)
        switchCompat = findViewById(R.id.switchCompat_switch)
        
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ConfigOptionSwitch)

        val iconResId = typedArray.getResourceId(R.styleable.ConfigOptionSwitch_icon, -1)
        val iconColor = typedArray.getColor(R.styleable.ConfigOptionSwitch_iconColor, -1)

        setIcon(AppCompatResources.getDrawable(context, iconResId), iconColor)

        setTitle(typedArray.getString(R.styleable.ConfigOptionSwitch_title) ?: "title")
        setSubtitle(typedArray.getString(R.styleable.ConfigOptionSwitch_subtitle) ?: "subtitle")

        typedArray.recycle()

        // Define o ouvinte de clique para alternar o estado do SwitchCompat
        setOnClickListener {
            
        }

        // Define o ouvinte de alteração de estado do SwitchCompat
        switchCompat.setOnCheckedChangeListener { _, isChecked ->
            // Notifica o ouvinte externo quando o estado do SwitchCompat é alterado
            onSwitchCheckedChangeListener?.invoke(isChecked)
        }
    }

    private fun setIcon(icon: Drawable?, color: Int) {
        icon?.let {
            val coloredIcon = it.mutate()
            if (color != -1) {
                coloredIcon.setTint(color)
            }
            iconImageView.setImageDrawable(coloredIcon)
        }
    }

    fun setTitle(title: String) {
        titleTextView.text = title
    }

    fun setSubtitle(subtitle: String) {
        subtitleTextView.text = subtitle
    }
	
	fun toggle() {
		switchCompat.toggle()
		invalidate();
		requestLayout();
	}

    // Ouvinte para ser notificado quando o estado do SwitchCompat é alterado
    private var onSwitchCheckedChangeListener: ((isChecked: Boolean) -> Unit)? = null

    // Define o ouvinte externo para o estado do SwitchCompat
    fun setOnSwitchCheckedChangeListener(listener: (isChecked: Boolean) -> Unit) {
        onSwitchCheckedChangeListener = listener
    }
}
