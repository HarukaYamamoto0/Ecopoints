
package com.economix.ecopoints.viewholder

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.economix.ecopoints.ui.custom.ExchangeOptionCard
import com.economix.ecopoints.model.ExchangeOptionData
import com.economix.ecopoints.R
import com.economix.ecopoints.utils.forAllChildren

class ExchangeOptionViewHolder(
    itemView: ExchangeOptionCard,
    private val fragmentManager: FragmentManager
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private lateinit var exchangeOptionData: ExchangeOptionData
	private val exchangeOptionCard: ExchangeOptionCard = itemView as ExchangeOptionCard

    init {
		itemView.forAllChildren {
			it.setOnClickListener(this)
		}
    }

    fun bind(data: ExchangeOptionData) {
        exchangeOptionData = data
        exchangeOptionCard.apply {
            setIcon(exchangeOptionData.icon)
            setText(exchangeOptionData.text)
        }
    }

    override fun onClick(view: View) {
        val dialog = Dialog(itemView.context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_info_card)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        
        val cardView: ImageView = dialog.findViewById(R.id.imageView_card)
		val titleView: TextView = dialog.findViewById(R.id.textView_title_card)
        val textView: TextView = dialog.findViewById(R.id.textView_description_card)
        
        cardView.setImageDrawable(ContextCompat.getDrawable(dialog.context, exchangeOptionData.icon))
		titleView.text = exchangeOptionData.text
        textView.text = exchangeOptionData.description
        
        dialog.show()
    }
}
