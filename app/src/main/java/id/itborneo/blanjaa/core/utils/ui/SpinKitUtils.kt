package id.itborneo.blanjaa.core.utils.ui

import android.view.View
import com.github.ybq.android.spinkit.SpinKitView

object SpinKitUtils {

    fun show(spinKitView: SpinKitView) {
        spinKitView.visibility = View.VISIBLE
    }

    fun hide(spinKitView: SpinKitView) {
        spinKitView.visibility = View.GONE
    }

}