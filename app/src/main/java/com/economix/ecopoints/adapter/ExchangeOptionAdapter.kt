package com.economix.ecopoints.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.economix.ecopoints.model.ExchangeOptionData
import com.economix.ecopoints.viewholder.ExchangeOptionViewHolder
import com.economix.ecopoints.ui.custom.ExchangeOptionCard
import android.widget.LinearLayout
import com.economix.ecopoints.R

class ExchangeOptionAdapter(
    private val context: Context,
    private val exchangeOptionDataList: List<ExchangeOptionData>,
    private val supportFragmentManager: FragmentManager
) : RecyclerView.Adapter<ExchangeOptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeOptionViewHolder {
		val context = parent.context
	    val exchangeOptionCard = ExchangeOptionCard(context)
	
		val layoutParams = ViewGroup.LayoutParams(
			ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.WRAP_CONTENT
	    )
		
		exchangeOptionCard.layoutParams = layoutParams
		return ExchangeOptionViewHolder(exchangeOptionCard, supportFragmentManager)
	}

    override fun onBindViewHolder(holder: ExchangeOptionViewHolder, position: Int) {
		val exchangeOptionData = exchangeOptionDataList[position]
		holder.bind(exchangeOptionData)
	}

    override fun getItemCount(): Int {
        return exchangeOptionDataList.size
    }
}
