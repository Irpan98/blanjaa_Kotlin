package id.itborneo.bacasuara.core.utils.intentUtils

import android.content.Context
import android.content.Intent
import android.net.Uri


object IntentAction {

    fun actionToBrowser(context: Context, url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }
}