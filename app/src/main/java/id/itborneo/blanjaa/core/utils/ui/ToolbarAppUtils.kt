package id.itborneo.blanjaa.core.utils.ui

import android.app.Activity
import android.widget.Toolbar


class ToolbarAppUtils(private val tbApp: Toolbar) {


    fun title(title: String) {
        tbApp.title = title

    }

    fun initBackButton(activity: Activity, backPressed: (Unit) -> Unit) {
//        tbApp.setNavigationIcon(R.drawable.ic_back)
        tbApp.setNavigationOnClickListener {
            backPressed(Unit)
            activity.onBackPressed()
        }

    }

}