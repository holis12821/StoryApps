package com.example.storyapps.utils

import android.content.Context
import android.content.res.Resources

object ScreenUtils {

    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun getScreenWidth() {
        Resources.getSystem().displayMetrics.widthPixels
    }

    fun convertDpToPixel(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }
}