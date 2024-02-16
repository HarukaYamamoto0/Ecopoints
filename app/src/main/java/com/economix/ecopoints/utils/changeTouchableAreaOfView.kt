package com.economix.ecopoints.utils

import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.view.TouchDelegate
import android.view.View

fun changeTouchableAreaOfView(view: View, extraSpaceInDp: Int) {
    val parent = view.parent as View

    Handler(Looper.getMainLooper()).post {
        val extraSpaceInPixels = (extraSpaceInDp * parent.resources.displayMetrics.density).toInt()

        val touchableArea = Rect()
        view.getHitRect(touchableArea)

        touchableArea.top -= extraSpaceInPixels
        touchableArea.bottom += extraSpaceInPixels
        touchableArea.left -= extraSpaceInPixels
        touchableArea.right += extraSpaceInPixels

        parent.touchDelegate = TouchDelegate(touchableArea, view)
    }
}
