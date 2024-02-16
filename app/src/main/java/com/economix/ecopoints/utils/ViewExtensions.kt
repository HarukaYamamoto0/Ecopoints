package com.economix.ecopoints.utils

import android.view.View
import android.view.ViewGroup

fun ViewGroup.forAllChildren(forOneChild: (v: View) -> Unit) {
	forOneChild(this)
	
	for (index in 0 until childCount) {
		val child = getChildAt(index)
		
		if (child is ViewGroup) child.forAllChildren(forOneChild)
		else forOneChild(child)
	}
}
