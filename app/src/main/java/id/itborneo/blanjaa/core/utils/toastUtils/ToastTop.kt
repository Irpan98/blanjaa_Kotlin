package id.itborneo.blanjaa.core.utils.toastUtils

import android.content.Context
import android.graphics.PorterDuff
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import id.itborneo.blanjaa.R

@Suppress("DEPRECATION")
object ToastTop {

    fun show(context: Context, warningText: String) {
        val toast = Toast.makeText(context, warningText, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP, 0, 20)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            toast.view?.background
                ?.setColorFilter(context.getColor(R.color.greenPrimary), PorterDuff.Mode.SRC_IN)
            val text: TextView? = toast.view?.findViewById(android.R.id.message)
            text?.setTextColor(context.getColor(R.color.white))
        }
        toast.show()
    }

}