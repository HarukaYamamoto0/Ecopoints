package com.economix.ecopoints.provider

import android.content.Context
import com.economix.ecopoints.model.ExchangeOptionData
import com.economix.ecopoints.R

object ExchangeOptionsProvider {
    fun getExchangeOptions(context: Context): List<ExchangeOptionData> {
        return listOf(
            ExchangeOptionData(
                R.drawable.ic_money,
                context.getString(R.string.exchange_money_title),
                R.drawable.card,
                context.getString(R.string.exchange_money_description)
            ),
            ExchangeOptionData(
                R.drawable.ic_coupons,
                context.getString(R.string.exchange_coupons_title),
                R.drawable.card,
                context.getString(R.string.exchange_coupons_description)
            ),
            ExchangeOptionData(
                R.drawable.ic_products,
                context.getString(R.string.exchange_products_title),
                R.drawable.card,
                context.getString(R.string.exchange_products_description)
            ),
            ExchangeOptionData(
                R.drawable.ic_transfer,
                context.getString(R.string.exchange_transfer_title),
                R.drawable.card,
                context.getString(R.string.exchange_transfer_description)
            ),
            ExchangeOptionData(
                R.drawable.ic_airplane,
                context.getString(R.string.exchange_travel_title),
                R.drawable.card,
                context.getString(R.string.exchange_travel_description)
            ),
            ExchangeOptionData(
                R.drawable.ic_gift,
                context.getString(R.string.exchange_gifts_title),
                R.drawable.card,
                context.getString(R.string.exchange_raffle_description)
            )
        )
    }
}
