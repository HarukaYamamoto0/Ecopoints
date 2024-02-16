package com.economix.ecopoints.model

import android.graphics.drawable.Drawable
import java.io.Serializable

data class ExchangeOptionData(
	var icon: Int,
    var text: String,
    val card: Int,
    val description: String
) : Serializable
