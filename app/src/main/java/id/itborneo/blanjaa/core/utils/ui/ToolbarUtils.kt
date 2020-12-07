package id.itborneo.blanjaa.core.utils.ui

import androidx.appcompat.app.AppCompatActivity

object ToolbarUtils {
    fun removeActionBar(activity: AppCompatActivity) {
        activity.supportActionBar?.hide()
    }



}